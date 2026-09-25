/**
 * 
 * @author กลุ่มหิว
 */
// หน้า UI การทำงานของการคำนวณอัตราการเผาผลาญพลังงาน
package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("bmr")
public class BMR extends VerticalLayout {

    public BMR() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // หัวข้อ
        Paragraph title = new Paragraph("คำนวณอัตราการเผาผลาญพลังงาน (BMR)");
        title.addClassNames(LumoUtility.FontSize.XXLARGE, LumoUtility.FontWeight.BOLD);

        // ช่องกรอกข้อมูล
        NumberField weightField = new NumberField("น้ำหนัก (กิโลกรัม)");
        weightField.setWidth("100%");
        weightField.getStyle().set("font-size", "18px");

        NumberField heightField = new NumberField("ส่วนสูง (เซนติเมตร)");
        heightField.setWidth("100%");
        heightField.getStyle().set("font-size", "18px");

        NumberField ageField = new NumberField("อายุ (ปี)");
        ageField.setWidth("100%");
        ageField.getStyle().set("font-size", "18px");

        // เลือกเพศ
        RadioButtonGroup<String> genderGroup = new RadioButtonGroup<>();
        genderGroup.setLabel("เพศ");
        genderGroup.setItems("ชาย", "หญิง");
        genderGroup.setWidth("100%");
        genderGroup.getStyle().set("font-size", "18px");

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
        // เพิ่มกรอบและข้อความ
        resultBox.add(result);
        resultBox1.add(result1);
        resultBox.setVisible(false);
        resultBox1.setVisible(false);

        // การคำนวณอัตราการเผาผลาญพลังงาน

        calculateButton.addClickListener(click -> {
            try {
                // เรียก validateInput เพื่อตรวจสอบข้อมูล
                if (validateInput(weightField, heightField, ageField, genderGroup, resultBox, resultBox1, result1)) {
                    // ถ้าข้อมูลถูกต้อง ให้ทำการคำนวณ BMR และแสดงผลลัพธ์
                    Double weight = weightField.getValue();
                    Double height = heightField.getValue();
                    Double age = ageField.getValue();
                    String gender = genderGroup.getValue();

                    boolean isMale = gender.equals("ชาย");

                    // สร้าง instance ของ BMRCalculator และคำนวณค่า BMR
                    BMRCalculator bmrCalculator = new BMRCalculator(weight, height, age.intValue(), isMale);
                    double bmr = bmrCalculator.calculate();

                    result.setText(String.format("อัตราการเผาผลาญพลังงาน: %.2f กิโลแคลอรี", bmr));
                    resultBox.setVisible(true);
                    resultBox1.setVisible(false);

                }
                // เคลียร์ข้อมูลในฟิลด์
                weightField.clear();
                heightField.clear();
                ageField.clear();
                genderGroup.clear();

            } catch (Exception e) {
                e.printStackTrace();
                result1.setText("เกิดข้อผิดพลาด กรุณาลองใหม่");
                resultBox1.setVisible(true);
            }
        });

        // ฟอร์ม
        VerticalLayout formLayout = new VerticalLayout(title, weightField, heightField, ageField, genderGroup,
                calculateButton, resultBox, resultBox1);
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
     * @param ageField    รับข้อมูลอายุ
     * @param genderGroup เลือกเพศ
     * @param resultBox   กรอบแสดงผลสีเขียว
     * @param resultBox1  กรอบแสดงผลสีแดง
     * @param result1     ข้อความ
     * @return false เมื่อข้อมูลที่กรอกมาไม่ถูกต้อง trueเมื่อข้อมูลที่กรอกมาถูกต้อง
     */

    // เช็คข้อมูลที่กรอกมา

    private boolean validateInput(NumberField weightField, NumberField heightField, NumberField ageField,
            RadioButtonGroup<String> genderGroup, Div resultBox, Div resultBox1, Paragraph result1) {
        Double weight = weightField.getValue();
        Double height = heightField.getValue();
        Double age = ageField.getValue();
        String gender = genderGroup.getValue();

        if (weight == null || weight <= 0 || height == null || height <= 0 || age == null || age <= 0
                || gender == null) {
            result1.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
            resultBox.setVisible(false);
            resultBox1.setVisible(true);
            return false;
        }

        return true;
    }
}
