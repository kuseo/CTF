// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.MotionEvent;

// Referenced classes of package android.support.v4.view:
//            MotionEventCompatEclair

public class MotionEventCompat
{
    static class BaseMotionEventVersionImpl
        implements MotionEventVersionImpl
    {

        public int findPointerIndex(MotionEvent motionevent, int i)
        {
            return i != 0 ? -1 : 0;
        }

        public int getPointerId(MotionEvent motionevent, int i)
        {
            if(i == 0)
                return 0;
            else
                throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float getX(MotionEvent motionevent, int i)
        {
            if(i == 0)
                return motionevent.getX();
            else
                throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float getY(MotionEvent motionevent, int i)
        {
            if(i == 0)
                return motionevent.getY();
            else
                throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        BaseMotionEventVersionImpl()
        {
        }
    }

    static class EclairMotionEventVersionImpl
        implements MotionEventVersionImpl
    {

        public int findPointerIndex(MotionEvent motionevent, int i)
        {
            return MotionEventCompatEclair.findPointerIndex(motionevent, i);
        }

        public int getPointerId(MotionEvent motionevent, int i)
        {
            return MotionEventCompatEclair.getPointerId(motionevent, i);
        }

        public float getX(MotionEvent motionevent, int i)
        {
            return MotionEventCompatEclair.getX(motionevent, i);
        }

        public float getY(MotionEvent motionevent, int i)
        {
            return MotionEventCompatEclair.getY(motionevent, i);
        }

        EclairMotionEventVersionImpl()
        {
        }
    }

    static interface MotionEventVersionImpl
    {

        public abstract int findPointerIndex(MotionEvent motionevent, int i);

        public abstract int getPointerId(MotionEvent motionevent, int i);

        public abstract float getX(MotionEvent motionevent, int i);

        public abstract float getY(MotionEvent motionevent, int i);
    }


    public MotionEventCompat()
    {
    }

    public static int findPointerIndex(MotionEvent motionevent, int i)
    {
        return IMPL.findPointerIndex(motionevent, i);
    }

    public static int getActionIndex(MotionEvent motionevent)
    {
        return (motionevent.getAction() & 0xff00) >> 8;
    }

    public static int getActionMasked(MotionEvent motionevent)
    {
        return motionevent.getAction() & 0xff;
    }

    public static int getPointerId(MotionEvent motionevent, int i)
    {
        return IMPL.getPointerId(motionevent, i);
    }

    public static float getX(MotionEvent motionevent, int i)
    {
        return IMPL.getX(motionevent, i);
    }

    public static float getY(MotionEvent motionevent, int i)
    {
        return IMPL.getY(motionevent, i);
    }

    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_MASK = 255;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_SCROLL = 8;
    static final MotionEventVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 5)
            IMPL = new EclairMotionEventVersionImpl();
        else
            IMPL = new BaseMotionEventVersionImpl();
    }
}
