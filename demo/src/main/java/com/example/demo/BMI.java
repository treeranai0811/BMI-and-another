/**
 * 
 * @author กลุ่มหิว
 */
// หน้า UI การทำงานของการคำนวณค่าดัชนีมวลกาย
package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("bmi")
public class BMI extends VerticalLayout {
    public BMI() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // หัวข้อ
        Paragraph title = new Paragraph("คำนวณค่าดัชนีมวลกาย (BMI)");
        title.addClassNames(LumoUtility.FontSize.XXLARGE, LumoUtility.FontWeight.BOLD);

        // ช่องกรอกข้อมูล
        NumberField weightField = new NumberField("น้ำหนัก (กิโลกรัม)");
        weightField.setWidth("100%");
        weightField.getStyle().set("font-size", "18px");

        NumberField heightField = new NumberField("ส่วนสูง (เซนติเมตร)");
        heightField.setWidth("100%");
        heightField.getStyle().set("font-size", "18px");
        // ปุ่มคำนวณ
        Button calculateButton = new Button("คำนวณ");
        calculateButton.setWidth("100%");
        calculateButton.getStyle().set("font-size", "18px");
        // ปุ่มเสร็จสิ้น
        Button backButton = new Button("เสร็จสิ้น", event -> UI.getCurrent().navigate(""));
        backButton.getElement().getStyle()
                .set("position", "absolute")
                .set("top", "10px")
                .set("right", "10px")
                .set("font-size", "18px");

        // กรอบแสดงผลสีเขียว
        Div resultBox = new Div();
        resultBox.addClassNames(LumoUtility.Padding.XLARGE, LumoUtility.Border.ALL,
                LumoUtility.BorderRadius.LARGE);
        resultBox.setWidth("100%");
        resultBox.getStyle().set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-height", "50px")
                .set("border-color", "#28a745")
                .set("border-width", "2px");
        // กรอบแสดงผลสีแดง
        Div resultBox1 = new Div();
        resultBox1.addClassNames(LumoUtility.Padding.XLARGE, LumoUtility.Border.ALL,
                LumoUtility.BorderRadius.LARGE);
        resultBox1.setWidth("100%");
        resultBox1.getStyle().set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-height", "50px")
                .set("border-color", "#FF0000")
                .set("border-width", "2px");
        // ข้อความ
        Paragraph result = new Paragraph();
        result.getStyle().set("font-size", "22px")
                .set("font-weight", "bold")
                .set("text-align", "center");
        // ข้อความ
        Paragraph result1 = new Paragraph();
        result1.getStyle().set("font-size", "22px")
                .set("font-weight", "bold")
                .set("text-align", "center");

        resultBox.add(result);
        resultBox1.add(result1);
        resultBox.setVisible(false);
        resultBox1.setVisible(false);

        // การคำนวณค่าดัชนีมวลกาย

        calculateButton.addClickListener(click -> {
            try {
                // เรียก validateInput เพื่อตรวจสอบข้อมูล
                if (validateInput(weightField, heightField, resultBox, resultBox1, result1)) {
                    // ถ้าข้อมูลถูกต้อง ให้ทำการคำนวณ BMI และแสดงผลลัพธ์
                    Double weight = weightField.getValue();
                    Double height = heightField.getValue();

                    // สร้าง instance ของ BMICalculator และคำนวณค่า BMI

                    BMICalculator bmiCalculator = new BMICalculator(weight, height);
                    double bmi = bmiCalculator.calculate();

                    String bmiCategory = getBMICategory(bmi);

                    result.getElement().setProperty("innerHTML",
                            String.format("ค่าดัชนีมวลกาย: %.2f<br>อยู่ในเกณฑ์: %s", bmi, bmiCategory));

                    resultBox.setVisible(true);
                    resultBox1.setVisible(false);

                }
                // เคลียร์ข้อมูลในฟิลด์
                weightField.clear();
                heightField.clear();
            } catch (Exception e) {
                e.printStackTrace();
                result.setText("เกิดข้อผิดพลาด กรุณาลองใหม่");
                resultBox.setVisible(true);
                resultBox1.setVisible(false);
            }
        });

        // ฟอร์ม
        VerticalLayout formLayout = new VerticalLayout(title, weightField, heightField, calculateButton, resultBox,
                resultBox1);
        formLayout.setAlignItems(Alignment.CENTER);
        formLayout.setPadding(true);
        formLayout.setSpacing(true);
        formLayout.setWidth("70%");
        formLayout.setMinHeight("80vh");

        // เมนูนำทาง
        MenuBar menuBar = new MenuBar();
        menuBar.addItem(new RouterLink("คำนวณค่าดัชนีมวลกาย (BMI)", BMI.class));
        menuBar.addItem(new RouterLink("คำนวณอัตราการเผาผลาญพลังงาน (BMR)", BMR.class));
        menuBar.addItem(new RouterLink("คำนวณพื้นผิวของร่างกาย (BSA)", BSA.class));
        menuBar.addItem(new RouterLink("คำนวณเปอร์เซ็นต์ไขมัน (BF)", BF.class));
        menuBar.addItem(new RouterLink("คำนวณน้ำหนักที่ควรจะเป็น (IBW)", IBW.class));
        // เพิ่มเมนูนำทาง ฟอร์ม ปุ่มเสร็จสิ้น
        add(menuBar, formLayout, backButton);
    }

    /**
     * 
     * @param weightField กล่องรับข้อมูลน้ำหนัก
     * @param heightField กล่องรับข้อมูลส่วนสูง
     * @param resultBox   กรอบแสดงผลสีเขียว
     * @param resultBox1  กรอบแสดงผลสีแดง
     * @param result1     ข้อความ
     * @return false เมื่อข้อมูลที่กรอกมาไม่ถูกต้อง trueเมื่อข้อมูลที่กรอกมาถูกต้อง
     */
    // เช็คข้อมูลที่กรอกมา
    private boolean validateInput(NumberField weightField, NumberField heightField, Div resultBox, Div resultBox1,
            Paragraph result1) {
        Double weight = weightField.getValue();
        Double height = heightField.getValue();

        if (weight == null || weight <= 0 || height == null || height <= 0) {
            result1.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
            resultBox.setVisible(false);
            resultBox1.setVisible(true);
            return false;
        }

        return true;
    }

    /**
     * 
     * @param bmi ค่า bmi ที่คำนวณได้
     * @return ข้อความแสดงผลว่า bmi อยู่ในเกณฑ์ไหน
     */
    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "ผอม หรือ น้ำหนักต่ำกว่ามาตรฐาน";
        } else if (bmi >= 18.5 && bmi < 22.9) {
            return "ร่างกายสมส่วน หรือ อยู่ในเกณฑ์ปกติ";
        } else if (bmi >= 22.9 && bmi < 24.9) {
            return "ภาวะน้ำหนักเกิน หรือ โรคอ้วนระดับที่ 1";
        } else if (bmi >= 24.9 && bmi < 29.9) {
            return "โรคอ้วน หรือ โรคอ้วนระดับที่ 2";
        } else {
            return "โรคอ้วนอันตราย โรคอ้วนระดับที่ 3";
        }
    }
}
