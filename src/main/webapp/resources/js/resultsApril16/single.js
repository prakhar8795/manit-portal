google.charts.load('43', { "packages": ["corechart"] });

$(document).ready(function () {
	$("#panelResult").hide();
	$("#txtSchNo").focus();
	$("#txtSchNo").keypress(function (e) {
		var key = e.which;
		if (key == 13)
			submitSchNo();
	});
	$("#btnSchNoSub").on('click', submitSchNo);
	$("#btnShowChart").on('click', showChart);
	// $("#btnDownloadPng").on('click', downloadBarChart);
});

function getStdData(schNo) {
	for (var i = 0; i < data.length; i++) {
		if (data[i].sch_no == schNo)
			return data[i];
	}
	return null;
}

stdData = null;
barChart = null;

function submitSchNo() {
	$("#panelResult").fadeOut();
	document.title = "Individual Results";
	var schNo = $("#txtSchNo").val();
	stdData = getStdData(schNo);
	if (stdData == null) {
		$("#modalSchNoResp .modal-body p").html('Result of <strong>' + schNo + '</strong> not found');
		$("#modalSchNoResp").modal('show');
	} else {
		$("#panelResult .panel-title").text(stdData.name + ' (' + stdData.sch_no + ')');
		$("#detailCourse").text(stdData.course);
		$("#detailBranch").text(stdData.branch);
		$("#detailSemester").text(stdData.semester);
		$("#detailMonthYear").text(stdData.month_year);

		var span = $("#detailSGPA span").text(stdData.sgpa);
		span.removeClass();
		span.addClass('label');
		var sgpa = parseFloat(stdData.sgpa);
		if (sgpa >= 8.00)
			span.addClass('label-primary');
		else if (sgpa >= 6.00)
			span.addClass('label-info');
		else if (sgpa >= 4.00)
			span.addClass('label-warning');
		else
			span.addClass('label-danger');

		span = $("#detailResult span").text(stdData.result);
		span.removeClass();
		span.addClass('label');
		if (stdData.result.toUpperCase() == 'PASS')
			span.addClass('label-success');
		else if (stdData.result.toUpperCase() == 'NOT CLEAR')
			span.addClass('label-danger');
		else
			span.addClass('label-warning');
		document.title = stdData.name;
		var subjectBody = document.getElementById('subjectBody');
		while (subjectBody.firstChild)
			subjectBody.removeChild(subjectBody.firstChild);
		for (var i = 0; i < stdData.subjects.length; i++) {
			var row = document.createElement('tr');

			var td = document.createElement('td');
			td.innerText = stdData.subjects[i].code;
			row.appendChild(td);

			td = document.createElement('td');
			td.innerText =stdData.subjects[i].name;
			row.appendChild(td);

			td = document.createElement('td');
			td.innerText = stdData.subjects[i].grade_points;
			row.appendChild(td);

			td = document.createElement('td');
			td.innerText = stdData.subjects[i].grade;
			row.appendChild(td);

			td = document.createElement('td');
			td.innerText = stdData.subjects[i].credit_earned + ' ('
				+ stdData.subjects[i].credit + ')';
			row.appendChild(td);

			subjectBody.appendChild(row);
		}
		$("#panelResult").fadeIn();
	}
}

function showChart() {
	$("#modalTitle").text(stdData.name + ' (' + stdData.sch_no + ')');
	$("#modalBarChart").modal('show');

	var dataArray = [['Subject', 'Grade Points', {"role": "style"}]];
	for (var i = 0; i < stdData.subjects.length; i++) {
		var hue = 1 - (parseFloat(stdData.subjects[i].grade_points) / 30);
		dataArray.push([stdData.subjects[i].code, parseFloat(stdData.subjects[i].grade_points)
			,'color: ' + hslToHex(hue, 1, .625)]);
	};
	var hue = 1 - (parseFloat(stdData.sgpa) / 30);
	dataArray.push(["SGPA", parseFloat(stdData.sgpa), 'color: ' + hslToHex(hue, 1, .625)]);
	var dataTable = google.visualization.arrayToDataTable(dataArray);
	var divChart = $("#modalBarChart .modal-body");
	var chartOptions = {
		"title": "Bar chart of Subject Grade Points and SGPA",
		"width": 880,
		"height": 495,
		"hAxis": {
			"minValue": 0,
			"maxValue": 10.0,
			"title": "Grade Points",
			"gridlines": {
				"count": 11
			}
		},
		"vAxis": {
			"textStyle": {
				"fontSize": 10
			},
			"title": "Subjects"
		},
		"bar": { "groupWidth": "75%" },
		"legend": {"position": "none"}
	};
	barChart = new google.visualization.BarChart(divChart[0]);
	barChart.draw(dataTable, chartOptions);

}

/* function downloadBarChart() {
	var anchor = document.createElement('a');
	anchor.href = barChart.getImageURI();
	anchor.download = stdData.sch_no + ' ' + stdData.name + '.png';
	anchor.style.display = 'none';
	document.body.appendChild(anchor);
	anchor.click();
	document.body.removeChild(anchor);
} */

/**
 * Converts an HSL color value to RGB. Conversion formula
 * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
 * Assumes h, s, and l are contained in the set [0, 1] and
 * returns r, g, and b in the set [0, 255].
 *
 * @param   Number  h       The hue
 * @param   Number  s       The saturation
 * @param   Number  l       The lightness
 * @return  Array           The RGB representation
 */
function hslToHex(h, s, l) {
	var r, g, b;

	if (s == 0) {
		r = g = b = l; // achromatic
	} else {
		var hue2rgb = function hue2rgb(p, q, t) {
			if (t < 0) t += 1;
			if (t > 1) t -= 1;
			if (t < 1 / 6) return p + (q - p) * 6 * t;
			if (t < 1 / 2) return q;
			if (t < 2 / 3) return p + (q - p) * (2 / 3 - t) * 6;
			return p;
		}

		var q = l < 0.5 ? l * (1 + s) : l + s - l * s;
		var p = 2 * l - q;
		r = Math.round(hue2rgb(p, q, h + 1 / 3) * 255);
		g = Math.round(hue2rgb(p, q, h) * 255);
		b = Math.round(hue2rgb(p, q, h - 1 / 3) * 255);
	}
	return rgbToHex(r, g, b);
}

function componentToHex(c) {
	var hex = c.toString(16);
	return hex.length == 1 ? "0" + hex : hex;
}

function rgbToHex(r, g, b) {
	return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}