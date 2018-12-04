package top.jplayer.baseprolibrary.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import top.jplayer.baseprolibrary.BaseInitApplication;

/**
 * Created by Obl on 2018/8/7.
 * top.jplayer.baseprolibrary.utils
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class FileUtil {
    public static File saveFile(InputStream is, String fileName) {
        try {
            File file = new File(BaseInitApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getFileInputStream(String path) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        }

        return fileInputStream;
    }

    public static byte[] getByteFromUri(Uri uri) {
        InputStream input = getFileInputStream(uri.getPath());

        Object var3;
        try {
            int count = 0;

            while (true) {
                if (count == 0) {
                    count = input.available();
                    if (count != 0) {
                        continue;
                    }
                }

                byte[] bytes = new byte[count];
                input.read(bytes);
                byte[] var4 = bytes;
                return var4;
            }
        } catch (Exception var14) {
            var3 = null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var13) {
                    ;
                }
            }

        }

        return (byte[]) var3;
    }

    public static void writeByte(Uri uri, byte[] data) {
        File fileFolder = new File(uri.getPath().substring(0, uri.getPath().lastIndexOf("/")));
        fileFolder.mkdirs();
        File file = new File(uri.getPath());

        try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            os.write(data);
            os.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static File convertBitmap2File(Bitmap bm, String dir, String name) {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File targetFile = new File(dirFile.getPath() + File.separator + name);
        if (targetFile.exists()) {
            targetFile.delete();
        }

        File tmpFile = new File(dirFile.getPath() + File.separator + name + ".tmp");

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile));
            bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        targetFile = new File(dirFile.getPath() + File.separator + name);
        return tmpFile.renameTo(targetFile) ? targetFile : tmpFile;
    }

    public static File copyFile(File src, File path, String name) {
        File dest = null;
        if (!src.exists()) {
            return dest;
        } else {

            dest = new File(path, name);

            try {
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }

                fos.flush();
                fos.close();
                fis.close();
                return dest;
            } catch (IOException var8) {
                var8.printStackTrace();
                return dest;
            }
        }
    }

    public static File copyFile(File src, String path, String name) {
        File dest = null;
        if (!src.exists()) {
            return dest;
        } else {
            dest = new File(path);
            if (!dest.exists()) {
                dest.mkdirs();
            }

            dest = new File(path + name);

            try {
                FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }

                fos.flush();
                fos.close();
                fis.close();
                return dest;
            } catch (IOException var8) {
                var8.printStackTrace();
                return dest;
            }
        }
    }

    public static byte[] file2byte(File file) {
        if (!file.exists()) {
            return null;
        } else {
            byte[] buffer = null;

            try {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];

                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }

                fis.close();
                bos.close();
                buffer = bos.toByteArray();
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return buffer;
        }
    }

    public static File byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;

        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            file = new File(dir.getPath() + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return file;
    }

    public static String getCachePath(Context context) {
        return getCachePath(context, "");
    }

    private static boolean hasFilePermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
    }

    public static String getCachePath(Context context, @NonNull String dir) {
        boolean sdCardExist = false;

        try {
            sdCardExist = Environment.getExternalStorageState().equals("mounted");
        } catch (ArrayIndexOutOfBoundsException var8) {
            var8.printStackTrace();
        }

        File cacheDir;
        if (Build.VERSION.SDK_INT < 19 && !hasFilePermission(context)) {
            cacheDir = context.getCacheDir();
        } else {
            try {
                cacheDir = context.getExternalCacheDir();
            } catch (ArrayIndexOutOfBoundsException var7) {
                var7.printStackTrace();
                cacheDir = null;
            }
        }

        if (!sdCardExist || cacheDir == null || !cacheDir.exists() && !cacheDir.mkdirs()) {
            cacheDir = context.getCacheDir();
        }

        File tarDir = new File(cacheDir.getPath() + File.separator + dir);
        if (tarDir.exists() && tarDir.isFile()) {
            tarDir.delete();
        }

        if (!tarDir.exists()) {
            boolean result = tarDir.mkdir();
            if (!result) {
                if (Build.VERSION.SDK_INT >= 23 && hasFilePermission(context)) {
                    tarDir = new File("/sdcard/cache/" + dir);
                    if (!tarDir.exists()) {
                        result = tarDir.mkdirs();
                    }
                } else {
                    File filesDir = context.getFilesDir();
                    tarDir = new File(filesDir, dir);
                    if (!tarDir.exists()) {
                        result = tarDir.mkdirs();
                    }
                }

            }
        }

        return tarDir.getPath();
    }

    public static String getInternalCachePath(Context context, @NonNull String dir) {
        File cacheDir = new File(context.getCacheDir().getPath() + File.separator + dir);
        if (!cacheDir.exists()) {
            boolean result = cacheDir.mkdir();
        }

        return cacheDir.getPath();
    }

    public static String getMediaDownloadDir(Context context) {
        boolean sdCardExist = Environment.getExternalStorageState().equals("mounted");
        String path = "/sdcard";
        if (sdCardExist) {
            File file = Environment.getExternalStorageDirectory();
            path = file.getPath();
        }

        try {
            Resources resources = context.getResources();
            String filePath = resources.getString(resources.getIdentifier("rc_media_message_default_save_path", "string", context.getPackageName()));
            path = path + filePath;
            File file = new File(path);
            if (!file.exists() && !file.mkdirs()) {
                path = "/sdcard";
            }
        } catch (Resources.NotFoundException var6) {
            var6.printStackTrace();
            path = "/sdcard";
        }

        return path;
    }

    public static long getFileSize(File file) {
        long size = 0L;

        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = (long) fis.available();
                if (fis != null) {
                    fis.close();
                }
            } else {
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return size;
    }
}
