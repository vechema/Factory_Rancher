package com.jegner.factory.rancher.resource;

import java.io.File;

public class GameResourceNames {
    public static final String dirtMapFileName = "dirt/dirtMap.tmx";

    public static final String outputFolder = "output";

    // Standard animation suffices
    private static final String atlasSuffix = ".atlas";
    private static final String walk = "_walk";
    private static final String idle = "_idle";
    private static final String eat = "_eat";

    private static final String down = "_down";
    private static final String up = "_up";
    private static final String right = "_right";


    // Human resources
    public static final String human = "human";
    public static final String humanAtlasFileName = outputFolder + "/" + human + atlasSuffix;
    public static final String humanWalkDown = human + walk + down;
    public static final String humanWalkUp = human + walk + up;
    public static final String humanWalkRight = human + walk + right;

    // Cow resources
    public static final String cow = "cow";
    public static final String cowAtlasFileName = outputFolder + "/" + cow + atlasSuffix;
    public static final String cowWalkDown = cow + walk + down;
    public static final String cowWalkUp = cow + walk + up;
    public static final String cowWalkRight = cow + walk + right;
    public static final String cowIdleDown = cow + idle + down;
    public static final String cowEatDown = cow + eat + down;

}
