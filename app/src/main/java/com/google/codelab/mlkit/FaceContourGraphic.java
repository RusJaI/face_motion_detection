package com.google.codelab.mlkit;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;

import java.util.List;

/** Graphic instance for rendering face contours graphic overlay view. */
public class FaceContourGraphic extends GraphicOverlay.Graphic {

  public FaceContourGraphic(GraphicOverlay overlay) {
    super(overlay);
  }

  public Bitmap crop_image(InputImage image, List<Face> faces){
    if (faces != null) {
      Face face=faces.get(0);

      float maxsize=face.getBoundingBox().width()*face.getBoundingBox().height();
      float tempsize;

      for (Face f:faces) {
        tempsize=f.getBoundingBox().width()*f.getBoundingBox().height();
        if(maxsize<tempsize){
          maxsize=tempsize;
          face=f;
        }
      }
      float x = translateX(face.getBoundingBox().centerX());
      float y = translateY(face.getBoundingBox().centerY());

      // Draws a bounding box around the face.
      float xOffset = scaleX(face.getBoundingBox().width() / 2.0f);
      float yOffset = scaleY(face.getBoundingBox().height() / 2.0f);
      float left = x - xOffset;
      float top = y - yOffset;
      float right = x + xOffset;
      float bottom = y + yOffset;

      Bitmap bitmap = image.getBitmapInternal();
      Bitmap bitmap1 = Bitmap.createBitmap(bitmap, (int)left, (int)top, (int)xOffset*2,(int)yOffset*2);
      return bitmap1;
    }
    return  null;
  }

  @Override
  public void draw(Canvas canvas) {

  }
}
