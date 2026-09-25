/**
 * 
 * @author กลุ่มหิว
 */

package com.example.demo;
// สืบทอดจากคลาส Body

public class BSACalculator extends Body {
    /**
     * 
     * @param weight ค่าน้ำหนักที่ผู้ใช้กรอกเข้ามา (กิโลกรัม)
     * @param height ค่าส่วนสูงที่ผู้ใช้กรอกเข้ามา (เมตร)
     */
    public BSACalculator(double weight, double height) {
        super(weight, height);
    }

    // ฟังก์ชันคำนวณค่าพื้นผิวของร่างกาย
    @Override
    public double calculate() {
        // เปลี่ยนส่วนสูงจากเซนติเมตรเป็นเมตร
        double heightM = height / 100;
        return Math.sqrt((weight * heightM) / 3600);
    }
}