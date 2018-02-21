
var onScanSuccess = function(data){
  	kony.print("Success!");
  	kony.print("data: " + data);
};

var onScanCancelled = function(data){
  	kony.print("Cancelled!");
  	kony.print("data: " + data);
};

var onScanFailure = function(data){
  	kony.print("Failed!");
  	kony.print("data: " + data);
};

var scan = function(/*event*/){
  	kony.print("scan");
  	var myScanner = new scanner.Scanner();
  	kony.print(myScanner);
  	kony.print(myScanner.scan);
	//Invokes method 'scan' on the object
	myScanner.scan(onScanSuccess, onScanCancelled, onScanFailure, 
		"DATA_MATRIX", //preferFrontCamera
		false, //preferFrontCamera
		true, //showFlipCameraButton,
		true, //showTorchButton,
		false, //torchOn,
		"Ready to scan your Henkel products", //promptMessage,
		5000, //resultDisplayDuration,
		"portrait", //orientation|portrait|landscape
		true, //beepOnScan,
		true //saveHistory
	);
  	kony.print("scan end");
};

//homeFrm.scanBtn.onClick(scan);
