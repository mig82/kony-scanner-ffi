
var onScanSuccess = function(data){
  	alert(2);
  	alert(data);
};

var scan = function(/*event*/){
  	alert(1);
  	var myScanner = new scanner.Scanner();
  	alert(myScanner);
  	alert(myScanner.scan);
	//Invokes method 'scan' on the object
	myScanner.scan(onScanSuccess);
};

//homeFrm.scanBtn.onClick(scan);
