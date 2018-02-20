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

    String[] methods = {};

    Library libs[] = null;
    public Library[] getClasses() {
        libs = new Library[1];
        libs[0] = new Scanner();
        return libs;
    }

    public N_scanner() {}

    public Object[] execute(int index, Object[] params) {
        // TODO Auto-generated method stub
        Object[] ret = null;

        int paramLen = params.length;
        int inc = 1;
        switch (index) {
            default: break;
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

        String[] methods = {
            scan
        };

        public Object createInstance(final Object[] params) {
            return new com.mig82.scanner.ScannerWrapper();
        }

        public Object[] execute(int index, Object[] params) {
            // TODO Auto-generated method stub
            Object[] ret = null;

            int paramLen = params.length;
            int inc = 1;
            switch (index) {
                case 0:
                    if (paramLen < 4 || paramLen > 5) {
                        return new Object[] {
                            new Double(100), "Invalid Params"
                        };
                    }
                    inc = 1;

                    com.konylabs.vm.Function onSuccess0 = null;
                    if (params[0 + inc] != null && params[0 + inc] != LuaNil.nil) {
                        onSuccess0 = (com.konylabs.vm.Function) params[0 + inc];
                    }
                    com.konylabs.vm.Function onCancelled0 = null;
                    if (params[1 + inc] != null && params[1 + inc] != LuaNil.nil) {
                        onCancelled0 = (com.konylabs.vm.Function) params[1 + inc];
                    }
                    com.konylabs.vm.Function onFailure0 = null;
                    if (params[2 + inc] != null && params[2 + inc] != LuaNil.nil) {
                        onFailure0 = (com.konylabs.vm.Function) params[2 + inc];
                    }
                    java.lang.Object config0 = null;
                    if (params[3 + inc] != null && params[3 + inc] != LuaNil.nil) {
                        config0 = (java.lang.Object) params[3 + inc];
                    }
                    ret = this.scan(params[0], onSuccess0, onCancelled0, onFailure0, config0);
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
        public final Object[] scan(Object self, com.konylabs.vm.Function inputKey0, com.konylabs.vm.Function inputKey1, com.konylabs.vm.Function inputKey2, java.lang.Object inputKey3) {

            Object[] ret = null;
            ((com.mig82.scanner.ScannerWrapper) self).scan((com.konylabs.vm.Function) inputKey0, (com.konylabs.vm.Function) inputKey1, (com.konylabs.vm.Function) inputKey2, inputKey3);

            ret = new Object[] {
                LuaNil.nil, new Double(0)
            };
            return ret;
        }
    }
};