classes = [];
classStdInd = [];
classSub = [];
for (var i = 0; i < data.length; i++) {
	var cr = data[i].course;
	var br = data[i].branch;
	var sem = data[i].semester;
	var ind = -1;
	for (var j = 0; j < classes.length; j++) {
		if (classes[j][0] == cr && classes[j][1] == br && classes[j][2] == sem) {
			ind = j;
			break;
		}
	}
	if (ind == -1) {
		classes.push([cr, br, sem]);
		classStdInd.push([]);
		classSub.push([]);
		ind = classes.length - 1;
	}
	var zeroCount = 0;
	for (var j = 0; j < data[i].subjects.length; j++) {
		var subStr = '(' + data[i].subjects[j].code + ') ' + data[i].subjects[j].name;
		// var subInd = classSub[ind].indexOf(subStr);
		var subInd = -1;
		for (var k = 0; k < classSub[ind].length; k++) {
			if (classSub[ind][k].startsWith('(' + data[i].subjects[j].code)) {
				subInd = k;
				break;
			}
		}
		if (subInd < 0)
			classSub[ind].push(subStr);
		if (parseFloat(data[i].subjects[j].grade_points) == 0.0)
			zeroCount++;
	}
	// student should get positive marks in atleast 50% subjects
	if (zeroCount * 100 / data[i].subjects.length < 50.0)
		classStdInd[ind].push(i);
}
for (var i = 0; i < classSub.length; i++)
	classSub[i].sort();

google.charts.load('43', { "packages": ["corechart", "table"] });

function addBranchSem() {
	var selBranchSem = document.getElementById('selBranchSem');
	var opt = document.createElement('option');
	opt.value = "-1";
	selBranchSem.appendChild(opt);
	for (var i = 0; i < classes.length; i++) {
		opt = document.createElement('option');
		opt.innerText = classes[i][0] + ', ' + classes[i][1] + ', Semester ' + classes[i][2];
		opt.value = i.toString();
		selBranchSem.appendChild(opt);
	}
}

function onClassChange(e) {
	$("#divClassResult").hide();
	var ind = e.target.value;
	if (ind < 0)
		return;

	// array of arrays storing marks of subjects and sgpa
	var subMarks = [];
	var subResult = [];
	var classSgpa = [];
	var classCgpa = [];
	var classResult = [0, 0];

	// dataTable for Table
	var dataTable = new google.visualization.DataTable();
	dataTable.addColumn('number', 'Scholar Number');
	dataTable.addColumn('string', 'Name');
	dataTable.addColumn('number', 'SGPA');
	dataTable.addColumn('number', 'CGPA');
	dataTable.addColumn('boolean', 'Result');
	for (var i = 0; i < classSub[ind].length; i++) {
		var subCode = classSub[ind][i].split(')', 1)[0].substr(1);
		dataTable.addColumn('number', subCode);
		subMarks.push([]);
		subResult.push([0, 0]);
	}

	for (var i = 0; i < classStdInd[ind].length; i++) {
		var dind = classStdInd[ind][i];
		var schNo = data[dind].sch_no;
		var row = [{ "v": parseInt(schNo), "f": schNo }, data[dind].name, parseFloat(data[dind].sgpa), parseFloat(data[dind].cgpa),
			data[dind].result.toUpperCase() == "PASS"];

		classSgpa.push(parseFloat(data[dind].sgpa));
		classCgpa.push(parseFloat(data[dind].cgpa));
		classResult[(data[dind].result.toUpperCase() == 'PASS')? 1 : 0]++;

		for (var j = 0; j < classSub[ind].length; j++)
			row.push({ "v": 0.00, "f": "" });
		for (var j = 0; j < data[dind].subjects.length; j++) {
			var subStr = '(' + data[dind].subjects[j].code + ') ' + data[dind].subjects[j].name;
			var ptr = data[dind].subjects[j].grade_points;
			// var sind = classSub[ind].indexOf(subStr);
			var sind = -1;
			for (var k = 0; k < classSub[ind].length; k++) {
				if (classSub[ind][k].startsWith('(' + data[dind].subjects[j].code)) {
					sind = k;
					break;
				}
			}
			row[sind + 5] = { "v": parseFloat(ptr), "f": ptr };

			if (parseFloat(ptr) > 0) {
				subMarks[sind].push(parseFloat(ptr));
				subResult[sind][(data[dind].subjects[j].grade.toUpperCase() == "F") ? 0 : 1]++;
			}
		}
		dataTable.addRow(row);
	}

	// create table
	var table = new google.visualization.Table(document.getElementById('divResultTable'));
	var options = {
		"showRowNumber": true,
		"width": "100%",
		"cssClassNames": {
			"headerRow": "google-visualization-table-tr-head tblHeaderRow",
			"tableRow": "tblRow"
		}
	};
	table.draw(dataTable, options);
	
	// show subject list
	var divSubList = document.getElementById('divSubList');
	divSubList.innerHTML = "";
	for (var i = 0; i < classSub[ind].length; i++) {
		var clsInd = classSub[ind][i].indexOf(')');
		var subCode = classSub[ind][i].substr(1, clsInd - 1);
		var subName = classSub[ind][i].substr(clsInd + 1);
		divSubList.innerHTML += '<div class="col-lg-1" style="text-align: right;"><strong>' + subCode + '</strong></div>';
		divSubList.innerHTML += '<div class="col-lg-5">' + subName + '</div>';
	}
	var divResultStats = document.getElementById('divResultStats');
	divResultStats.innerHTML = '<h2>Statistics and Visual representation</h2>';

	$("#divClassResult").show();

	addStatPanel(divResultStats, 'SGPA: Sessional Grade Point Average', classSgpa, classResult, false);
	// addStatPanel(divResultStats, 'SGPA: Sessional Grade Point Average', classCgpa, classResult, false);
	for (var i = 0; i < classSub[ind].length; i++) {
		addStatPanel(divResultStats, classSub[ind][i], subMarks[i], subResult[i], false);
	}
}

function addStatPanel(divParent, heading, marksArr, resPair, showMaxMarks) {
	var panel = document.createElement('div');
	panel.classList.add('panel');
	panel.classList.add('panel-primary');
	divParent.appendChild(panel);
	
	var panelHead = document.createElement('div');
	panelHead.classList.add('panel-heading');
	panelHead.innerText = heading;
	panel.appendChild(panelHead);
	
	var panelBody = document.createElement('div');
	panelBody.classList.add('panel-body');
	panelBody.classList.add('container-fluid');
	panel.appendChild(panelBody);
	
	var row = document.createElement('div');
	row.classList.add('row');
	panelBody.appendChild(row);

	var divStat = document.createElement('div');
	divStat.classList.add('col-lg-6');
	row.appendChild(divStat);
	
	var inRow = document.createElement('div');
	inRow.classList.add('row');
	inRow.classList.add('inRow');
	divStat.appendChild(inRow);
	inRow.innerHTML = '<div class="col-lg-8 statProp">Mean: </div>' +
		'<div class="col-lg-4">' + mean(marksArr).toFixed(3) + '</div>';

	inRow = document.createElement('div');
	inRow.classList.add('row');
	inRow.classList.add('inRow');
	divStat.appendChild(inRow);
	inRow.innerHTML = '<div class="col-lg-8 statProp">Median: </div>' +
		'<div class="col-lg-4">' + median(marksArr).toFixed(3) + '</div>';

	inRow = document.createElement('div');
	inRow.classList.add('row');
	inRow.classList.add('inRow');
	divStat.appendChild(inRow);
	inRow.innerHTML = '<div class="col-lg-8 statProp">Standard Deviation: </div>' +
		'<div class="col-lg-4">' + Math.sqrt(variance(marksArr)).toFixed(3) + '</div>';
	
	inRow = document.createElement('div');
	inRow.classList.add('row');
	inRow.classList.add('inRow');
	divStat.appendChild(inRow);
	inRow.innerHTML = '<div class="col-lg-8 statProp">Skewness: </div>' +
		'<div class="col-lg-4">' + skewness(marksArr).toFixed(3) + '</div>';
	
	inRow = document.createElement('div');
	inRow.classList.add('row');
	inRow.classList.add('inRow');
	divStat.appendChild(inRow);
	inRow.innerHTML = '<div class="col-lg-8 statProp">Kurtosis: </div>' +
		'<div class="col-lg-4">' + kurtosis(marksArr).toFixed(3) + '</div>';

	if (showMaxMarks) {
		inRow = document.createElement('div');
		inRow.classList.add('row');
		inRow.classList.add('inRow');
		divStat.appendChild(inRow);
		inRow.innerHTML = '<div class="col-lg-8 statProp">Maximum Marks: </div>' +
			'<div class="col-lg-4">' + maximumMarks(marksArr) + '</div>';
	}

	var divPie = document.createElement('div');
	divPie.classList.add('col-lg-6');
	row.appendChild(divPie);

	var pieData = google.visualization.arrayToDataTable([
		['Result', 'Number of students'],
		['Pass', resPair[1]],
		['Not Clear', resPair[0]]
	]);
	var pieOptions = {
		"width": 300,
		"height": 200,
		"chartArea": {
			width: "90%",
			height: "90%"
		},
		"slices": {
			0: { "color": "green" },
			1: { "color": "red" }
		}
	};
	var pieChart = new google.visualization.PieChart(divPie);
	pieChart.draw(pieData, pieOptions);

	var divHistCont = document.createElement('div');
	divHistCont.classList.add('divHistCont');
	panelBody.appendChild(divHistCont);
	var divHistogram = document.createElement('div');
	divHistCont.appendChild(divHistogram);
	
	var histArr = [['Student', 'Marks']];
	for (var i = 0; i < marksArr.length; i++) {
		histArr.push(['', marksArr[i] >= 10.0 ? 9.99 : marksArr[i]]);
	}
	var histData = google.visualization.arrayToDataTable(histArr);
	var histOptions = {
		'chartArea': {
			'width': 640
		},
		'legend': { 'position': 'none' },
		'width': divParent.style.width,
		'height': 360,
		'histogram': {
			'bucketSize': 0.5,
			'hideBucketItems': true
		}
	};
	var histogram = new google.visualization.Histogram(divHistogram);
	histogram.draw(histData, histOptions);
}

$(document).ready(function () {
	$("#divClassResult").hide();
	addBranchSem();
	$("#selBranchSem").change(onClassChange);
});
