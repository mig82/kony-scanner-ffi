
var onScanSuccess = function(data){
  	kony.print("onScanSuccess!");
  	kony.print("data: " + data);
};

var scan = function(/*event*/){
  	kony.print("scan");
  	var myScanner = new scanner.Scanner();
  	kony.printlert(myScanner);
  	kony.print(myScanner.scan);
	//Invokes method 'scan' on the object
	myScanner.scan(onScanSuccess);
  	kony.print("scan end");
};

//homeFrm.scanBtn.onClick(scan);
