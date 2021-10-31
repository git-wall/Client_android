package com.example.client_chat.Surface;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.AnimationsSet;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.ScaleSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Fit;
import su.levenetc.android.textsurface.contants.TYPE;

public class SurfaceScaleSample {
    public static void play(TextSurface textSurface) {

        Text textA = TextBuilder.create("You want to chat ?").setPosition(Align.SURFACE_CENTER).build();
        Text textB = TextBuilder.create("Make more friend ?").setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textA).build();
        Text textC = TextBuilder.create("Let go!").setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textB).build();

        textSurface.play(TYPE.SEQUENTIAL,
                Alpha.show(textA, 500),
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.PARALLEL, Alpha.show(textB, 500), Alpha.hide(textA, 500)),
                        new ScaleSurface(500, textB, Fit.WIDTH)
                ),
                Delay.duration(1000),
                new AnimationsSet(TYPE.PARALLEL,
                        new AnimationsSet(TYPE.PARALLEL, Alpha.show(textC, 500), Alpha.hide(textB, 500)),
                        new ScaleSurface(500, textC, Fit.WIDTH)
                )
        );
    }
}
