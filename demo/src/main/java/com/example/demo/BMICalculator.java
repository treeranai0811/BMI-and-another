/**
 * 
 * @author กลุ่มหิว
 */

package com.example.demo;
// สืบทอดจากคลาส Body

public class BMICalculator extends Body {
    /**
     * 
     * @param weight ค่าน้ำหนักที่ผู้ใช้กรอกเข้ามา (กิโลกรัม)
     * @param height ค่าส่วนสูงที่กรอกเข้ามา (เมตร)
     */
    public BMICalculator(double weight, double height) {
        super(weight, height); // เรียก constructor ของคลาส Body
    }

    // ฟังก์ชันคำนวณค่าดัชนีมวลกาย
    @Override
    public double calculate() {
        // เปลี่ยนส่วนสูงจากเซนติเมตรเป็นเมตร
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }
}
