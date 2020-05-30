package android.example.tic_tac_toe;

import android.content.Context;
import android.example.tic_tac_toe.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class PixelGridViewSingle extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint yellowPaint = new Paint();
    private boolean[][] cellCheckedX;
    private boolean[][] cellCheckedO;
    private boolean cellWinnerX = false;
    private boolean cellWinnerO = false;
    /*int color = Color.parseColor("#010010");*/

    /*MediaPlayer buttonClick = MediaPlayer.create(PixelGridViewSingle.this, R.raw.buttonClick);*/

    GlobalClass globalClass = (GlobalClass) getContext();


    private int count = 0;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    Canvas canvas;
    Bitmap canvasBitmapX;
    Bitmap canvasBitmapO;


    public PixelGridViewSingle(Context context) {
        this(context, null);


    }

    public PixelGridViewSingle(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setTextSize(100);
        /*canvas.drawColor(color);
*/
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();

        canvasBitmapX = BitmapFactory.decodeResource(getResources(), R.drawable.x)
                .copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(canvasBitmapX);

        canvasBitmapO = BitmapFactory.decodeResource(getResources(), R.drawable.o)
                .copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(canvasBitmapO);


    }


    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellCheckedX = new boolean[numColumns][numRows];
        cellCheckedO = new boolean[numColumns][numRows];

        for (int i = 0; i < getNumColumns(); i++) {
            for (int j = 0; j < getNumRows(); j++) {
                cellCheckedX[i][j] = false;
                cellCheckedO[i][j] = false;
            }
        }
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 1; i < numColumns - 1; i++) {
            for (int j = 1; j < numRows - 1; j++) {
                if (cellCheckedX[i][j] && (!cellCheckedO[i][j])) {

                    canvas.drawBitmap(canvasBitmapX, null, new RectF((i * cellWidth) + (cellWidth / 12),
                                    (j * cellHeight) + ((cellHeight - cellWidth) / 2),
                                    ((i + 1) * cellWidth) - (cellWidth / 12),
                                    ((j + 1) * cellHeight) - ((cellHeight - cellWidth) / 2)),
                            null);
                    checkWinnerX(i, j);
                    playO(i,j);
                    setCount((getCount()) + 1);
                }
                else if(cellCheckedO[i][j] && ( !cellCheckedX[i][j] ) ) {

                    canvas.drawBitmap(canvasBitmapO, null, new RectF((i * cellWidth) + (cellWidth / 12),
                                    (j * cellHeight) + ((cellHeight - cellWidth) / 2),
                                    ((i + 1) * cellWidth) - (cellWidth / 12),
                                    ((j + 1) * cellHeight) - ((cellHeight - cellWidth) / 2)),
                            null);
                    checkWinnerO(i,j);
                }
            }
        }


        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, cellHeight, i * cellWidth, (height - cellHeight), blackPaint);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(cellWidth, i * cellHeight, (width - cellWidth), i * cellHeight, blackPaint);
        }

        if (cellWinnerX) {
            canvas.drawText(" X WON", getWidth() / 2, cellHeight / 2, yellowPaint);
        } else if (cellWinnerO) {
            canvas.drawText(" O WON", getWidth() / 2, cellHeight / 2, yellowPaint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int column = (int) (event.getX() / cellWidth);
                int row = (int) (event.getY() / cellHeight);

                cellCheckedX[column][row] = true;
                setCount((getCount()) + 1);
                globalClass.playTouchSound();
                /*buttonClick.start();*/
                invalidate();

            }

            return true;
    }

    public void checkWinnerX(int i, int j){

        if(i ==1 && j==1){
            if(cellCheckedX[2][1] || cellCheckedX[1][2] || cellCheckedX[2][2]){
                if(cellCheckedX[3][1] || cellCheckedX[1][3] || cellCheckedX[3][3]){
                    cellWinnerX = true;
                }
            }
        }
        else if(i ==2 && j==1){
            if(cellCheckedX[1][1] && cellCheckedX[3][1]){
                cellWinnerX = true;

            }
            else if(cellCheckedX[2][2] && cellCheckedX[2][3]){
                cellWinnerX = true;
            }
        }
        else if( i == 3 && j==1){
            if(cellCheckedX[2][1] || cellCheckedX[3][2] || cellCheckedX[2][2]){
                if(cellCheckedX[1][1] || cellCheckedX[3][3] || cellCheckedX[1][3]){
                    cellWinnerX = true;
                }
            }
        }
        else if( i==1 && j==2){
            if(cellCheckedX[1][1] && cellCheckedX[1][3] ){
                cellWinnerX = true;
            }
            else if(cellCheckedX[2][2] && cellCheckedX[3][2]){
                cellWinnerX = true;
            }
        }
        else if( i==2 && j==2){
            if(cellCheckedX[1][1] && cellCheckedX[3][3]){
                cellWinnerX = true;
            }
            else if(cellCheckedX[1][3] && cellCheckedX[3][1]){
                cellWinnerX = true;
            }
        }
        else if( i==3 && j==2){
            if(cellCheckedX[3][1] && cellCheckedX[3][3]){
                cellWinnerX = true;
            }
            else if(cellCheckedX[2][2] && cellCheckedX[1][2]){
                cellWinnerX = true;
            }
        }
        else if( i== 1 && j==3){
            if(cellCheckedX[1][2] || cellCheckedX[2][3] || cellCheckedX[2][2]){
                if(cellCheckedX[1][1] || cellCheckedX[3][3] || cellCheckedX[3][1]){
                    cellWinnerX = true;
                }
            }
        }
        else if(i ==2 && j==3){
            if(cellCheckedX[1][3] && cellCheckedX[3][3]){
                cellWinnerX = true;
            }
            else if(cellCheckedX[2][2] && cellCheckedX[2][1]){
                cellWinnerX = true;
            }
        }
        else if( i==3 && j==3){
            if(cellCheckedX[2][3] || cellCheckedX[3][2] || cellCheckedX[2][2]){
                if(cellCheckedX[1][3] || cellCheckedX[3][1] || cellCheckedX[1][1]){
                    cellWinnerX = true;
                }
            }

        }
    }

    public void checkWinnerO(int i, int j){


        if(i ==1 && j==1){
            if(cellCheckedO[2][1] || cellCheckedO[1][2] || cellCheckedO[2][2]){
                if(cellCheckedO[3][1] || cellCheckedO[1][3] || cellCheckedO[3][3]){
                    cellWinnerO = true;
                }
            }
        }
        else if(i ==2 && j==1){
            if(cellCheckedO[1][1] && cellCheckedO[3][1]){
                cellWinnerO = true;

            }
            else if(cellCheckedO[2][2] && cellCheckedO[2][3]){
                cellWinnerO = true;
            }
        }
        else if( i == 3 && j==1){
            if(cellCheckedO[2][1] || cellCheckedO[3][2] || cellCheckedO[2][2]){
                if(cellCheckedO[1][1] || cellCheckedO[3][3] || cellCheckedO[1][3]){
                    cellWinnerO = true;
                }
            }
        }
        else if( i==1 && j==2){
            if(cellCheckedO[1][1] && cellCheckedO[1][3] ){
                cellWinnerO = true;
            }
            else if(cellCheckedO[2][2] && cellCheckedO[3][2]){
                cellWinnerO = true;
            }
        }
        else if( i==2 && j==2){
            if(cellCheckedO[1][1] && cellCheckedO[3][3]){
                cellWinnerO = true;
            }
            else if(cellCheckedO[1][3] && cellCheckedO[3][1]){
                cellWinnerO = true;
            }
        }
        else if( i==3 && j==2){
            if(cellCheckedO[3][1] && cellCheckedO[3][3]){
                cellWinnerO = true;
            }
            else if(cellCheckedO[2][2] && cellCheckedO[1][2]){
                cellWinnerO = true;
            }
        }
        else if( i== 1 && j==3){
            if(cellCheckedO[1][2] || cellCheckedO[2][3] || cellCheckedO[2][2]){
                if(cellCheckedO[1][1] || cellCheckedO[3][3] || cellCheckedO[3][1]){
                    cellWinnerO = true;
                }
            }
        }
        else if(i ==2 && j==3){
            if(cellCheckedO[1][3] && cellCheckedO[3][3]){
                cellWinnerO = true;
            }
            else if(cellCheckedO[2][2] && cellCheckedO[2][1]){
                cellWinnerO = true;
            }
        }
        else if( i==3 && j==3){
            if(cellCheckedO[2][3] || cellCheckedO[3][2] || cellCheckedO[2][2]){
                if(cellCheckedO[1][3] || cellCheckedO[3][1] || cellCheckedO[1][1]){
                    cellWinnerO = true;
                }
            }

        }
    }

    public void playO(int i, int j) {
        if (i == 1 && j == 1) {
            if (cellCheckedX[2][1]) {
                cellCheckedO[3][1] = true;
            } else if (cellCheckedX[1][2]) {
                cellCheckedO[1][3] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[3][3] = true;
            } else {
                putO();

            }
        } else if (i == 2 && j == 1) {
            if (cellCheckedX[1][1]) {
                cellCheckedO[3][1] = true;
            } else if (cellCheckedX[3][1]) {
                cellCheckedO[1][1] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[2][3] = true;
            } else {
                putO();
            }
        } else if (i == 3 && j == 1) {
            if (cellCheckedX[2][1]) {
                cellCheckedO[1][1] = true;
            } else if (cellCheckedX[3][2]) {
                cellCheckedO[3][3] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[1][3] = true;
            } else {
                putO();
            }
        } else if (i == 1 && j == 2) {
            if (cellCheckedX[1][1]) {
                cellCheckedO[1][3] = true;
            } else if (cellCheckedX[1][3]) {
                cellCheckedO[1][1] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[3][2] = true;
            } else {
                putO();
            }
        } else if (i == 2 && j == 2) {
            if (cellCheckedX[1][1]) {
                cellCheckedO[3][3] = true;
            } else if (cellCheckedX[3][3]) {
                cellCheckedO[1][1] = true;
            } else if (cellCheckedX[2][1]) {
                cellCheckedO[2][3] = true;
            } else if (cellCheckedX[2][3]) {
                cellCheckedO[2][1] = true;
            } else if (cellCheckedX[3][1]) {
                cellCheckedO[1][3] = true;
            } else if (cellCheckedX[1][3]) {
                cellCheckedO[3][1] = true;
            } else if (cellCheckedX[1][2]) {
                cellCheckedO[3][2] = true;
            } else if (cellCheckedX[3][2]) {
                cellCheckedO[1][2] = true;
            } else {
                putO();
            }
        } else if (i == 3 && j == 2) {
            if (cellCheckedX[3][1]) {
                cellCheckedO[3][3] = true;
            } else if (cellCheckedX[3][3]) {
                cellCheckedO[3][1] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[1][2] = true;
            } else {
                putO();
            }
        } else if (i == 1 && j == 3) {
            if (cellCheckedX[1][2]) {
                cellCheckedO[1][1] = true;
            } else if (cellCheckedX[2][3]) {
                cellCheckedO[3][3] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[3][1] = true;
            } else {
                putO();
            }
        } else if (i == 2 && j == 3) {
            if (cellCheckedX[1][3]) {
                cellCheckedO[3][3] = true;
            } else if (cellCheckedX[3][3]) {
                cellCheckedO[1][3] = true;
            } else if (cellCheckedX[2][2]) {
                cellCheckedO[2][1] = true;
            } else {
                putO();
            }
        }
        else if( i==3 && j==3){
            if(cellCheckedX[2][3]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedX[3][2]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedX[2][2]){
                cellCheckedO[1][1] = true;
            }
            else{
                putO();
            }
        }
    }

    public void putO(){
        if(cellCheckedO[1][1]){
            if(cellCheckedO[2][1]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedO[1][2]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[3][3] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[2][1] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[1][2] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }
            }
        }
        else if(cellCheckedO[2][1]){
            if(cellCheckedO[1][1]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedO[3][1]){
                cellCheckedO[1][1] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[2][3] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[1][1] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[3][1] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }
            }
        }
        else if(cellCheckedO[3][1]){
            if(cellCheckedO[2][1]){
                cellCheckedO[1][1] = true;
            }
            else if(cellCheckedO[3][2]){
                cellCheckedO[3][3] = true;
             }
            else if(cellCheckedO[2][2]){
                cellCheckedO[1][3] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[2][1] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[3][2] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else if(cellCheckedO[1][2]){
            if(cellCheckedO[1][1]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedO[1][3]){
                cellCheckedO[1][1] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[3][2] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[1][1] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[1][3] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else if(cellCheckedO[2][2]){
            if(cellCheckedO[1][1]){
                cellCheckedO[3][3] = true;
            }
            else if(cellCheckedO[3][3]){
                cellCheckedO[1][1] = true;
            }
            else if(cellCheckedO[2][1]){
                cellCheckedO[2][3] = true;
            }
            else if(cellCheckedO[2][3]){
                cellCheckedO[2][1] = true;
            }
            else if(cellCheckedO[3][1]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedO[1][3]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedO[1][2]){
                cellCheckedO[3][2] = true;
            }
            else if(cellCheckedO[3][2]){
                cellCheckedO[1][2] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(8);
                if(randomNumber == 0){
                    cellCheckedO[3][3] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[1][1] = true;
                }
                else if(randomNumber == 2){
                    cellCheckedO[2][3] = true;
                }
                else if(randomNumber == 3){
                    cellCheckedO[2][1] = true;
                }
                else if(randomNumber == 4){
                    cellCheckedO[1][3] = true;
                }
                else if(randomNumber == 5){
                    cellCheckedO[3][1] = true;
                }
                else if(randomNumber == 6){
                    cellCheckedO[3][2] = true;
                }
                else{
                    cellCheckedO[1][2] = true;
                }
            }
        }
        else if(cellCheckedO[3][2]){
            if(cellCheckedO[3][1]){
                cellCheckedO[3][3] = true;
            }
            else if(cellCheckedO[3][3]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[1][2] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[3][1] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[3][3] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else if(cellCheckedO[1][3]){
            if(cellCheckedO[1][2]){
                cellCheckedO[1][1] = true;
            }
            else if(cellCheckedO[2][3]){
                cellCheckedO[3][3] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[3][1] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[1][3] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[2][3] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else if(cellCheckedO[2][3]){
            if(cellCheckedO[1][3]){
                cellCheckedO[3][3] = true;
            }
            else if(cellCheckedO[3][3]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[2][1] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[1][3] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[3][3] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else if(cellCheckedO[3][3]){
            if(cellCheckedO[2][3]){
                cellCheckedO[1][3] = true;
            }
            else if(cellCheckedO[3][2]){
                cellCheckedO[3][1] = true;
            }
            else if(cellCheckedO[2][2]){
                cellCheckedO[1][1] = true;
            }
            else{
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber == 0){
                    cellCheckedO[2][3] = true;
                }
                else if(randomNumber == 1){
                    cellCheckedO[3][2] = true;
                }
                else{
                    cellCheckedO[2][2] = true;
                }

            }
        }
        else{
            Random random = new Random();
            int firstRandomNumber = random.nextInt(9);
            if(firstRandomNumber == 0){
                if(!cellCheckedX[1][1]) {
                    cellCheckedO[1][1] = true;
                }
            }
            else  if(firstRandomNumber == 1){
                if(!cellCheckedX[2][1]) {
                    cellCheckedO[2][1] = true;
                }
            }
            else  if(firstRandomNumber == 2){
                if(!cellCheckedX[3][1]) {
                    cellCheckedO[3][1] = true;
                }
            }
            else  if(firstRandomNumber == 3){
                if(!cellCheckedX[3][1]) {
                    cellCheckedO[3][1] = true;
                }
            }
            else  if(firstRandomNumber == 4){
                if(!cellCheckedX[1][2]) {
                    cellCheckedO[1][2] = true;
                }
            }
            else  if(firstRandomNumber == 5){
                if(!cellCheckedX[2][2]) {
                    cellCheckedO[2][2] = true;
                }
            }
            else  if(firstRandomNumber == 6){
                if(!cellCheckedX[3][2]) {
                    cellCheckedO[3][2] = true;
                }
            }
            else  if(firstRandomNumber == 7){
                if(!cellCheckedX[1][3]) {
                    cellCheckedO[1][3] = true;
                }
            }
            else  if(firstRandomNumber == 8){
                if(!cellCheckedX[2][3]) {
                    cellCheckedO[2][3] = true;
                }
            }
            else {
                if(!cellCheckedX[3][3]) {
                    cellCheckedO[3][3] = true;
                }
            }
        }
    }


}