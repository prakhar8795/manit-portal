function mean(arr) {
	var sum = 0;
	for (var i = 0; i < arr.length; i++)
		sum += arr[i];
	return (sum / arr.length);
}

function median(arr) {
	var l = arr.length;
	arr.sort();
	if (l % 2 == 0) {
		return (arr[(l - 2) >> 1] + arr[l >> 1]) / 2;
	}
	return arr[l >> 1];
}

function variance(arr) {
	var avg = mean(arr);
	var sum = 0;
	for (var i = 0; i < arr.length; i++)
		sum += (arr[i] - avg) * (arr[i] - avg);
	return (sum / arr.length);
}

function skewness(arr) {
	var avg = mean(arr);
	var sd = Math.sqrt(variance(arr));
	var sum = 0;

	for (var i = 0; i < arr.length; i++) {
		var d = arr[i] - avg;
		sum += d * d * d;
	}

	return (sum / (arr.length * sd * sd * sd));
}

function kurtosis(arr) {
	var avg = mean(arr);
	var sdsq = variance(arr);
	var sum = 0;

	for (var i = 0; i < arr.length; i++) {
		var d = arr[i] - avg;
		sum += d * d * d * d;
	}

	return (sum / (arr.length * sdsq * sdsq));
}

/* I dont think this thing below is statistics */
function maximumMarks(arr) {
	var maxMarks = 0;
	var minError = Number.POSITIVE_INFINITY;
	for (var i = 1; i <= 100; i++) {
		var error = 0;
		for (var j = 0; j < arr.length; j++) {
			var marks = arr[j] * i / 10;
			error += Math.abs(marks - Math.round(marks));
		}
		if (error < minError) {
			minError = error;
			maxMarks = i;
		}
	}
	// console.log(minError);
	return maxMarks;
}