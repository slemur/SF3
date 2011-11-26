package net.aurora.graphics.detect;

import net.aurora.graphics.GraphicalObjectDefinition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:27
 *         Fetches all blobs on screen
 */
public class BlobDetector {

    public static ArrayList<Rectangle> getResults(GraphicalObjectDefinition objectDefinition, BufferedImage image, Color[][] ORI_COLMAP) {
        ArrayList<Rectangle> extracts = new ArrayList<Rectangle>();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color c = new Color(image.getRGB(x, y));
                if (c.getRed() > 250 && c.getGreen() > 250 && c.getBlue() > 250) {
                    ArrayList<Color> colors = new ArrayList<Color>();
                    Rectangle bounds = new Rectangle(x, y, objectDefinition.getWidth(), objectDefinition.getHeight());
                    int concentration = 0;

                    for (int subX = x; subX < (x + objectDefinition.getWidth()); subX++) {
                        for (int subY = y; subY < (y + objectDefinition.getHeight()); subY++) {
                            if (subX < image.getWidth() && subY < image.getHeight()) {
                                Color color = new Color(image.getRGB(subX, subY));
                                if (color.getRed() > 240 && color.getGreen() > 240 && color.getBlue() > 240) {
                                    if (!colors.contains(ORI_COLMAP[subX][subY])) {
                                        colors.add(ORI_COLMAP[subX][subY]);
                                    }
                                    concentration++;
                                }
                            }
                        }
                    }

                    if (concentration > objectDefinition.getThreshold() && colors.size() >= objectDefinition.getColors().length) {
                        extracts.add(bounds);
                    }
                }
            }
        }

        ArrayList<Rectangle> result = new ArrayList<Rectangle>();
        List<Set<Rectangle>> extractGroups = mergeIntersectingRects(extracts);
        for (Set<Rectangle> groups : extractGroups) {
            Rectangle base = null;

            for (Rectangle extract : groups) {
                if (base == null) {
                    base = extract;
                } else {
                    if (base.intersects(extract) || base.contains(extract) || extract.contains(base)) {
                        base.add(extract);
                    } else {
                        int baseX = base.x;
                        int exX = extract.x;

                        if (baseX < exX) {
                            // extract is right of base
                            if (Point2D.distance(baseX + base.width, base.getCenterY(), exX, extract.getCenterY()) < 2) {
                                base.add(extract);
                            }
                        } else {
                            //extract is left of base
                            if (Point2D.distance(exX + extract.width, extract.getCenterY(), baseX, base.getCenterY()) < 2) {
                                base.add(extract);
                            }
                        }
                    }
                }
            }
            result.add(base);
        }
        return result;
    }

    private static Set<Rectangle> getIntersections(List<Rectangle> list,
                                            Rectangle r) {
        Set<Rectangle> intersections = new HashSet<Rectangle>();
        intersections.add(r);

        Set<Rectangle> newIntersectionsToBeAdded = new HashSet<Rectangle>();

        do {
            newIntersectionsToBeAdded.clear();
            for (Rectangle r1 : list) {
                for (Rectangle r2 : intersections) {
                    if (!intersections.contains(r1) && (r2.intersects(r1) || r2.contains(r1))) {
                        newIntersectionsToBeAdded.add(r1);
                    }
                }
            }
            intersections.addAll(newIntersectionsToBeAdded);
        } while (!newIntersectionsToBeAdded.isEmpty());
        return intersections;
    }

    private static List<Set<Rectangle>> mergeIntersectingRects(List<Rectangle> allRects) {
        List<Set<Rectangle>> grouped = new ArrayList<Set<Rectangle>>();
        while (!allRects.isEmpty()) {
            Set<Rectangle> intersections = getIntersections(allRects, allRects.get(0));
            grouped.add(intersections);
            allRects.removeAll(intersections);
        }
        return grouped;
    }

}
