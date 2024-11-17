package com.dewu_rpc;

import android.annotation.TargetApi;
import android.app.AndroidAppHelper;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.virjar.sekiro.business.api.SekiroClient;
import com.virjar.sekiro.business.api.core.eventbus.event.client.SekiroClientConnectEvent;
import com.virjar.sekiro.business.api.interfaze.ActionHandler;
import com.virjar.sekiro.business.api.interfaze.HandlerRegistry;
import com.virjar.sekiro.business.api.interfaze.SekiroRequest;
import com.virjar.sekiro.business.api.interfaze.SekiroRequestInitializer;
import com.virjar.sekiro.business.api.interfaze.SekiroResponse;
import com.virjar.sekiro.business.api.log.SekiroLogger;

import java.util.Base64;
import java.util.UUID;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Entry implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        log("lpparam.packageName: " + lpparam.packageName);

        if (lpparam.packageName.equals("com.c2vl.kgamebox")) {

            log("Enter! Hook start");
            toast(AndroidAppHelper.currentApplication().getApplicationContext(), "Hook start");

            XposedHelpers.findAndHookMethod("com.netease.htprotect.factory.JNIFactory", lpparam.classLoader, "r25d273c7ad4065c3", byte[].class, int.class, int.class, boolean.class, int.class, boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    log("Enter! beforeHookedMethod");
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
        }
        if (lpparam.packageName.equals("com.c2vl.kgamebox")) {
            log("Enter!");

            XposedHelpers.findAndHookMethod("com.c2vl.kgamebox.library.u1", lpparam.classLoader, "b", java.lang.String.class, java.lang.String.class, long.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    log("Enter com.c2vl.kgamebox.library.u1!");
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });



//            XposedHelpers.findAndHookMethod("com.c2vl.kgamebox.library.u1", lpparam.classLoader, "b", java.lang.String.class, java.lang.String.class, long.class, int.class,new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//
//                    Class<?> Clazz = XposedHelpers.findClass("com.shizhuang.stone.main.SzSdk", lpparam.classLoader);
//                    Class<?> Clazz2 = XposedHelpers.findClass("com.shizhuang.dusanwa.main.SwSdk", lpparam.classLoader);
//                    String group = "dewu";
//                    String clientId = UUID.randomUUID().toString();
//                    String serverHost = "1.14.71.249";
//                    int serverPord = 5612;/*sekiro的端口*/
//                    SekiroClient sekiroClient = new SekiroClient(group, clientId, serverHost, serverPord);
//                    SekiroLogger.tag = "dewu_rpc==>";
//
//                    sekiroClient.addEventListener(new SekiroClientConnectEvent() {
//                        @Override
//                        public void onClientConnected(SekiroClient sekiroClient) {
//                            toast(AndroidAppHelper.currentApplication().getApplicationContext(), "连接服务器成功!");
//                        }
//                    });
//
//
//
//                    sekiroClient.setupSekiroRequestInitializer(new SekiroRequestInitializer() {
//                        @Override
//                        public void onSekiroRequest(SekiroRequest sekiroRequest, HandlerRegistry handlerRegistry) {
//                            handlerRegistry.registerSekiroHandler(new ActionHandler() {
//                                @Override
//                                public String action() {
//                                    return "dewu_ltk";
//                                }
//
//                                @TargetApi(Build.VERSION_CODES.O)
//                                @Override
//                                public void handleRequest(SekiroRequest sekiroRequest, SekiroResponse sekiroResponse) {
//                                    long timestamp = sekiroRequest.getLongValue("timestamp");
//                                    String uuid = sekiroRequest.getString("uuid");
//                                    if (uuid == null) {
//                                        sekiroResponse.failed("arg empty");
//                                    } else {
//                                        log("arg:" + uuid + " " + timestamp);
//                                        String result = (String) XposedHelpers.callStaticMethod(Clazz, "ltk", "", uuid, timestamp);
//                                        sekiroResponse.success(result);
//                                    }
//                                }
//                            });
//
//                            handlerRegistry.registerSekiroHandler(new ActionHandler() {
//                                @Override
//                                public String action() {
//                                    return "dewu_achilles";
//                                }
//
//                                @TargetApi(Build.VERSION_CODES.O)
//                                @Override
//                                public void handleRequest(SekiroRequest sekiroRequest, SekiroResponse sekiroResponse) {
//                                    byte[] data = Base64.getDecoder().decode(sekiroRequest.getString("data"));
//                                    String str = "nszAvJTGPbmWxEqHv03b4";
//                                    int i = 3;
//                                    long j = System.currentTimeMillis();
//                                    byte[] result = (byte[]) XposedHelpers.callStaticMethod(Clazz2, "achilles", data, str, i, j);
//                                    sekiroResponse.success(bytesToHex(result));
//                                }
//                            });
//
//
//
//                        }
//                    }).start();
//
//
//                }
//            });
        }
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    private static void log(String msg) {
        Log.e("dewu_rpc==>", msg);
        XposedBridge.log(msg);
    }


    public static void toast(Context context, String msg) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
    }

}
