package com.example.client_chat.Surface;

import android.graphics.Color;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.AnimationsSet;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Axis;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.TYPE;

public class Rotation3DSample {
    public static void play(TextSurface textSurface) {
        Text textA = TextBuilder.create("You want to chat ?").setSize(30).setColor(Color.BLACK).setPosition(Align.SURFACE_CENTER).build();
        Text textB = TextBuilder.create("Make more friend ?").setSize(30).setColor(Color.BLACK).setPosition(Align.SURFACE_CENTER, textA).build();
        Text textC = TextBuilder.create("Let go!").setSize(30).setColor(Color.BLACK).setPosition(Align.SURFACE_CENTER, textB).build();
        int duration = 1010;

        textSurface.play(TYPE.SEQUENTIAL,
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromCenter(textA, duration, Direction.CLOCK, Axis.X),
                        Rotate3D.hideFromCenter(textA, duration, Direction.CLOCK, Axis.Y)
                ),
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromSide(textB, duration, Pivot.LEFT),
                        Rotate3D.hideFromSide(textB, duration, Pivot.RIGHT)
                ),
                new AnimationsSet(TYPE.SEQUENTIAL,
                        Rotate3D.showFromSide(textC, duration, Pivot.TOP),
                        Rotate3D.hideFromSide(textC, duration, Pivot.BOTTOM)
                )
        );
    }
}
