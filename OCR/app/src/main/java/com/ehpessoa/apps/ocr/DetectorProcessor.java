package com.ehpessoa.apps.ocr;

import android.util.Log;
import android.util.SparseArray;

import com.ehpessoa.apps.ocr.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 *
 * Make this implement Detector.Processor<TextBlock> and add text to the GraphicOverlay.
 * Once this is implemented, override the abstract methods.
 */
public class DetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<Graphic> mGraphicOverlay;

    DetectorProcessor(GraphicOverlay<Graphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    @Override
    public void release() {
        mGraphicOverlay.clear();
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();

        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);

            if (item != null && item.getValue() != null) {
                Log.d("Processor", "Text detected! " + item.getValue());
            }

            Graphic graphic = new Graphic(mGraphicOverlay, item);
            mGraphicOverlay.add(graphic);
        }
    }
}
