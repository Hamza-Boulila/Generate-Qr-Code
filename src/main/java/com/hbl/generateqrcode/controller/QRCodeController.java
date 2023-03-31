package com.hbl.generateqrcode.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import com.hbl.generateqrcode.component.QRCodeGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class QRCodeController {
    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping("/qrcode")
    public void generateQRCode(@RequestParam("text") String text, HttpServletResponse response) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 400, 400);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
        response.flushBuffer();
    }

//    @GetMapping("/generateQRCode")
//    public void generateQRCode(HttpServletResponse response, @RequestParam String data) throws Exception {
//        ByteArrayOutputStream out = QRCode.from(data).stream();
//        response.setContentType("image/png");
//        response.setContentLength(out.size());
//        response.getOutputStream().write(out.toByteArray());
//        response.getOutputStream().flush();
//    }

}
