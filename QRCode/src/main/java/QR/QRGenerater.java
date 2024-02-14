package QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRGenerater {

    private static final int QR_CODE_SIZE = 300;

    public static void main(String[] args) {
        String qrCodeData = "Welcome To Numetry Technologies";
        String filePath = "C:\\Users\\JOHN\\Downloads\\qr-code.png";

        generateQRCodeImage(qrCodeData, filePath);
        System.out.println("QR Code generated successfully.");
    }

    private static void generateQRCodeImage(String text, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);

            BufferedImage image = new BufferedImage(QR_CODE_SIZE, QR_CODE_SIZE, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, QR_CODE_SIZE, QR_CODE_SIZE);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < QR_CODE_SIZE; i++) {
                for (int j = 0; j < QR_CODE_SIZE; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, "PNG", new File(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}