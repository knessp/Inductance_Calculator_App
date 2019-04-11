package com.ookttah.philip.mutualinductance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DrawingView2 drawView;
    EditText screenWidthCmText;
    EditText planeHeightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //redacted
    }


    /** Called when the user taps the clearMessage button */
    public void clearMessage(View view) { // Do something in response to button
        //redacted
    }
    /** Called when the user taps the aboutMessage button */
    public void aboutMessage(View view) { // Do something in response to button
        //redacted
    }

    /** Called when the user taps the calculateMessage button */
    public void calculateMessage(View view) { // Do something in response to button
        boolean findL;
        float screenWidthCmEdit = //redacted // init to actual screen width
        float planeHeightCmEdit = 0.1f;
        try {
            screenWidthCmEdit = Float.parseFloat(screenWidthCmText.getText().toString());
        } catch(NumberFormatException e) {}
        try {
            planeHeightCmEdit = Float.parseFloat(planeHeightText.getText().toString());
        } catch(NumberFormatException e) {}
        double metersPerPixel = (double) screenWidthCmEdit/100/widthInPixels;
        double planeHeight = (double) planeHeightCmEdit/100; //[meters]
        double[][] curve1Values = new double[1][1];
        double[][] curve2Values = new double[1][1];
        curve1Values = drawView.getCurve1();
        curve2Values = drawView.getCurve2();
        TextView textView = findViewById(R.id.textView);

        double mu = 12.57e-7;
        int NumLineSegs1 = drawView.curve1i-1; 
        int NumLineSegs2 = drawView.curve2i-1;

        if (NumLineSegs1<2)
            //redacted
        if (NumLineSegs2<2) {
            //redacted
        }else{
            //redacted
        }

        double xdiff1[] = new double[NumLineSegs1];
        double ydiff1[] = new double[NumLineSegs1];
        double xdiff2[] = new double[NumLineSegs2];
        double ydiff2[] = new double[NumLineSegs2];
        double SectionsLength1[] = new double[NumLineSegs1];
        double SectionsLength2[] = new double[NumLineSegs2];
        double SectionsAngle1[] = new double[NumLineSegs1];
        double SectionsAngle2[] = new double[NumLineSegs2];

        for (int i = 0; i < NumLineSegs1; i++) {
            xdiff1[i] = //redacted
            ydiff1[i] = //redacted
            SectionsLength1[i] = Math.sqrt(xdiff1[i]*xdiff1[i] + ydiff1[i]*ydiff1[i])*metersPerPixel;
            SectionsAngle1[i] = Math.atan(ydiff1[i]/xdiff1[i]);
            if (xdiff1[i] == 0)
                SectionsAngle1[i] = //redacted
        }
        for (int i = 0; i < NumLineSegs2; i++) {
            xdiff2[i] = //redacted 
            ydiff2[i] = //redacted
            SectionsLength2[i] = Math.sqrt(xdiff2[i]*xdiff2[i] + ydiff2[i]*ydiff2[i])*metersPerPixel;
            SectionsAngle2[i] = Math.atan(ydiff2[i]/xdiff2[i]);
            if (xdiff2[i] == 0)
                SectionsAngle2[i] = //redacted
        }
        for (int i = 0; i < NumLineSegs1; i++) {
            if (xdiff1[i] < 0) {
                SectionsAngle1[i] = //redacted
            }
        }
        for (int i = 0; i < NumLineSegs2; i++) {
            if (xdiff2[i] < 0) {
                SectionsAngle2[i] = //redacted
            }
        }

        double M = 0;
        double dotAngle = 0;
        double distance = 0;
        double distanceX = 0;
        double distanceY = 0;
        for (int Seg1=0; Seg1<NumLineSegs1; Seg1++) {
            for (int Seg2 = 0; Seg2 < NumLineSegs2; Seg2++) {
                dotAngle = Math.cos(SectionsAngle1[Seg1] - SectionsAngle2[Seg2]);
                distanceX = (curve1Values[Seg1][0]-curve2Values[Seg2][0])*metersPerPixel; // [meters]
                distanceY = (curve1Values[Seg1][1]-curve2Values[Seg2][1])*metersPerPixel; // [meters]
                distance = Math.sqrt( distanceX*distanceX + distanceY*distanceY + planeHeight*planeHeight ); // [meters]
                M = M + SectionsLength1[Seg1]*SectionsLength2[Seg2]*dotAngle/distance;
            }
        }
        M = Math.abs(M*mu/(4*3.14));
        if(findL) {
            if (M < 1e-9 && M > 1e-12) {
                textView.setText("L = " + String.format("%.5g%n", M / 1e-12) + " pH");
            } else if (M < 1e-6 && M > 1e-9) {
                textView.setText("L = " + String.format("%.5g%n", M / 1e-9) + " nH");
            } else if (M < 1e-3 && M > 1e-6) {
                textView.setText("L = " + String.format("%.5g%n", M / 1e-6) + " uH");
            } else {
                textView.setText("L = " + String.format("%.9g%n", M) + " H");
            }
        }else {
            if (M < 1e-9 && M > 1e-12) {
                textView.setText("M = " + String.format("%.5g%n", M / 1e-12) + " pH");
            } else if (M < 1e-6 && M > 1e-9) {
                textView.setText("M = " + String.format("%.5g%n", M / 1e-9) + " nH");
            } else if (M < 1e-3 && M > 1e-6) {
                textView.setText("M = " + String.format("%.5g%n", M / 1e-6) + " uH");
            } else {
                textView.setText("M = " + String.format("%.9g%n", M) + " H");
            }
        }
    }


}
