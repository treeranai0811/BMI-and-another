/**
 * 
 * @author กลุ่มหิว
 */

//หน้าแรกของเว็บ

package com.example.demo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class HomeView extends VerticalLayout {
    public HomeView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // ข้อความต้อนรับ
        Paragraph welcomeText = new Paragraph("ยินดีต้อนรับสู่เว็บคำนวณสุขภาพ");
        welcomeText.getStyle().set("font-size", "2.5rem").set("color", "#28a745");

        // คำอธิบาย
        Paragraph description = new Paragraph(
                "เราช่วยคุณในการคำนวณค่าดัชนีมวลกาย (BMI), การเผาผลาญพลังงาน (BMR),");
        description.getStyle().set("font-size", "1.2em").set("color", "#555").set("text-align", "center");

        Paragraph description2 = new Paragraph(
                "พื้นผิวของร่างกาย (BSA), เปอร์เซ็นต์ไขมัน (BF), และน้ำหนักที่ควรจะเป็น (IBW)");
        description2.getStyle().set("font-size", "1.2em").set("color", "#555").set("text-align", "center");

        Paragraph description3 = new Paragraph("ㅤ");// บรรทัดล่องหน
        description2.getStyle().set("font-size", "1.2em").set("color", "#555").set("text-align", "center");

        // ลิงก์ไปหน้าคำนวณ BMI
        RouterLink bmiLink = new RouterLink("เริ่มต้นคำนวณ", BMI.class);
        bmiLink.getStyle()
                .set("background-color", "#28a745")
                .set("color", "white")
                .set("padding", "15px 25px")
                .set("font-size", "1.2rem")
                .set("border-radius", "8px")
                .set("text-decoration", "none")
                .set("transition", "background-color 0.3s");

        // เพิ่มเนื้อหาไปยังเลย์เอาท์
        Div container = new Div(welcomeText, description, bmiLink);
        container.getStyle().set("text-align", "center").set("max-width", "600px");
        add(container);

        // กรอบรอบข้อความและปุ่ม
        Div card = new Div(welcomeText, description, description2, description3, bmiLink);
        card.getStyle()
                .set("border", "2px solid #28a745")
                .set("border-radius", "12px")
                .set("padding", "20px")
                .set("box-shadow", "2px 4px 10px rgba(0, 0, 0, 0.1)")
                .set("background-color", "#f9f9f9")
                .set("text-align", "center")
                .set("max-width", "700px")
                .set("height", "300px"); // เพิ่มความสูงเป็น 300px

        add(card);

    }
}