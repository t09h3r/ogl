package com.example.gdc11;

import android.opengl.*;

public class cell{
  private float pos[]=new float[4];

// New class members
/** Store our model data in a float buffer. */
private final FloatBuffer mTriangle1Vertices;
//private final FloatBuffer mTriangle2Vertices;
//private final FloatBuffer mTriangle3Vertices;
 
/** How many bytes per float. */
private final int mBytesPerFloat = 4;

// New class members
/** Allocate storage for the final combined matrix. This will be passed into the shader program. */
private float[] mMVPMatrix = new float[16];
 
/** How many elements per vertex. */
private final int mStrideBytes = 7 * mBytesPerFloat;
 
/** Offset of the position data. */
private final int mPositionOffset = 0;
 
/** Size of the position data in elements. */
private final int mPositionDataSize = 3;
 
/** Offset of the color data. */
private final int mColorOffset = 3;
 
/** Size of the color data in elements. */
private final int mColorDataSize = 4;
 

  public void Render(){
aTriangleBuffer.position(mPositionOffset);
    GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
            mStrideBytes, aTriangleBuffer);
 
    GLES20.glEnableVertexAttribArray(mPositionHandle);
 
    // Pass in the color information
    aTriangleBuffer.position(mColorOffset);
    GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
            mStrideBytes, aTriangleBuffer);
 
    GLES20.glEnableVertexAttribArray(mColorHandle);
 
    // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
    // (which currently contains model * view).
    Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
 
    // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
    // (which now contains model * view * projection).
    Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
 
    GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
    GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
  }
  public cell(float p[]){
    pos[0]=p[0];
    pos[1]=p[1];
    pos[2]=p[2];
  //  pos[3]=p[3];

    // This triangle is red, green, and blue.
    final float[] triangle1VerticesData = {
            // X, Y, Z,
            // R, G, B, A
            -0.5f, -0.25f, 0.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
 
            0.5f, -0.25f, 0.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
 
            0.0f, 0.559016994f, 0.0f,
            0.0f, 1.0f, 0.0f, 1.0f};
 
 
    // Initialize the buffers.
    mTriangle1Vertices = ByteBuffer.allocateDirect(triangle1VerticesData.length * mBytesPerFloat)
    .order(ByteOrder.nativeOrder()).asFloatBuffer();
 
 
    mTriangle1Vertices.put(triangle1VerticesData).position(0);

  }
}
