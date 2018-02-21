package com.konylabs.ffi;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import com.konylabs.api.TableLib;
import com.konylabs.vm.LuaTable;



import com.mig82.scanner.ScannerWrapper;
import com.konylabs.libintf.Library;
import com.konylabs.libintf.JSLibrary;
import com.konylabs.vm.LuaError;
import com.konylabs.vm.LuaNil;


public class N_scanner extends JSLibrary {

 
	String[] methods = { };


 Library libs[] = null;
 public Library[] getClasses() {
 libs = new Library[1];
 libs[0] = new Scanner();
 return libs;
 }



	public N_scanner(){
	}

	public Object[] execute(int index, Object[] params) {
		// TODO Auto-generated method stub
		Object[] ret = null;
 
		int paramLen = params.length;
 int inc = 1;
		switch (index) {
 		default:
			break;
		}
 
		return ret;
	}

	public String[] getMethods() {
		// TODO Auto-generated method stub
		return methods;
	}
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "scanner";
	}


	/*
	 * return should be status(0 and !0),address
	 */
 


class Scanner extends JSLibrary {

 
 
	public static final String scan = "scan";
 
	String[] methods = { scan };

	public Object createInstance(final Object[] params) {
 return new com.mig82.scanner.ScannerWrapper(
 );
 }


	public Object[] execute(int index, Object[] params) {
		// TODO Auto-generated method stub
		Object[] ret = null;
 
		int paramLen = params.length;
 int inc = 1;
		switch (index) {
 		case 0:
 if (paramLen < 13 || paramLen > 14){ return new Object[] {new Double(100),"Invalid Params"};}
 inc = 1;
 
 com.konylabs.vm.Function onSuccess0 = null;
 if(params[0+inc] != null && params[0+inc] != LuaNil.nil) {
 onSuccess0 = (com.konylabs.vm.Function)params[0+inc];
 }
 com.konylabs.vm.Function onCancelled0 = null;
 if(params[1+inc] != null && params[1+inc] != LuaNil.nil) {
 onCancelled0 = (com.konylabs.vm.Function)params[1+inc];
 }
 com.konylabs.vm.Function onFailure0 = null;
 if(params[2+inc] != null && params[2+inc] != LuaNil.nil) {
 onFailure0 = (com.konylabs.vm.Function)params[2+inc];
 }
 java.lang.String formats0 = null;
 if(params[3+inc] != null && params[3+inc] != LuaNil.nil) {
 formats0 = (java.lang.String)params[3+inc];
 }
 Boolean preferFrontCamera0 = null;
 if(params[4+inc] != null && params[4+inc] != LuaNil.nil) {
 preferFrontCamera0 = (Boolean)params[4+inc];
 }
 Boolean showFlipCameraButton0 = null;
 if(params[5+inc] != null && params[5+inc] != LuaNil.nil) {
 showFlipCameraButton0 = (Boolean)params[5+inc];
 }
 Boolean showTorchButton0 = null;
 if(params[6+inc] != null && params[6+inc] != LuaNil.nil) {
 showTorchButton0 = (Boolean)params[6+inc];
 }
 Boolean torchOn0 = null;
 if(params[7+inc] != null && params[7+inc] != LuaNil.nil) {
 torchOn0 = (Boolean)params[7+inc];
 }
 java.lang.String promptMessage0 = null;
 if(params[8+inc] != null && params[8+inc] != LuaNil.nil) {
 promptMessage0 = (java.lang.String)params[8+inc];
 }
 Double resultDisplayDuration0 = null;
 if(params[9+inc] != null && params[9+inc] != LuaNil.nil) {
 resultDisplayDuration0 = (Double)params[9+inc];
 }
 java.lang.String orientation0 = null;
 if(params[10+inc] != null && params[10+inc] != LuaNil.nil) {
 orientation0 = (java.lang.String)params[10+inc];
 }
 Boolean beepOnScan0 = null;
 if(params[11+inc] != null && params[11+inc] != LuaNil.nil) {
 beepOnScan0 = (Boolean)params[11+inc];
 }
 Boolean saveHistory0 = null;
 if(params[12+inc] != null && params[12+inc] != LuaNil.nil) {
 saveHistory0 = (Boolean)params[12+inc];
 }
 ret = this.scan(params[0]
 ,onSuccess0
 ,onCancelled0
 ,onFailure0
 ,formats0
 ,preferFrontCamera0
 ,showFlipCameraButton0
 ,showTorchButton0
 ,torchOn0
 ,promptMessage0
 ,resultDisplayDuration0
 ,orientation0
 ,beepOnScan0
 ,saveHistory0
 );
 
 			break;
 		default:
			break;
		}
 
		return ret;
	}

	public String[] getMethods() {
		// TODO Auto-generated method stub
		return methods;
	}
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "Scanner";
	}

	/*
	 * return should be status(0 and !0),address
	 */
 
 
 	public final Object[] scan( Object self ,com.konylabs.vm.Function inputKey0
 ,com.konylabs.vm.Function inputKey1
 ,com.konylabs.vm.Function inputKey2
 ,java.lang.String inputKey3
 ,Boolean inputKey4
 ,Boolean inputKey5
 ,Boolean inputKey6
 ,Boolean inputKey7
 ,java.lang.String inputKey8
 ,Double inputKey9
 ,java.lang.String inputKey10
 ,Boolean inputKey11
 ,Boolean inputKey12
 ){
 
		Object[] ret = null;
 ((com.mig82.scanner.ScannerWrapper)self).scan( (com.konylabs.vm.Function)inputKey0
 , (com.konylabs.vm.Function)inputKey1
 , (com.konylabs.vm.Function)inputKey2
 , inputKey3
 , inputKey4.booleanValue() , inputKey5.booleanValue() , inputKey6.booleanValue() , inputKey7.booleanValue() , inputKey8
 , inputKey9.intValue() , inputKey10
 , inputKey11.booleanValue() , inputKey12.booleanValue() );
 
 ret = new Object[]{LuaNil.nil, new Double(0)};
 		return ret;
	}
 
}

};
