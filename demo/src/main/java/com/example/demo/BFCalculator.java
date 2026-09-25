/**
 * 
 * @author กลุ่มหิว
 */

package com.example.demo;

// สืบทอดจากคลาส Body
public class BFCalculator extends Body {

    private double age;
    private boolean isMale;

    /**
     * 
     * @param weight   ค่าน้ำหนักที่ผู้ใช้กรอกเข้ามา (กิโลกรัม)
     * @param heightCm ค่าส่วนสูงที่ผู้ใช้กรอกเข้ามา (เซนติเมตร)
     * @param age      ค่ายุของผู้ใช้ที่กรอกเข้ามา (ปี)
     * @param isMale   เพศที่ผู้ใช้กรอกเข้ามา
     */
    public BFCalculator(double weight, double heightCm, double age, boolean isMale) {
        super(weight, heightCm / 100); // แปลงจากเซนติเมตรเป็นเมตรและส่งไปที่คลาส Body
        this.age = age;
        this.isMale = isMale;
    }

    // ฟังก์ชันคำนวณเปอร์เซ็นต์ไขมันในร่างกาย
    @Override
    public double calculate() {
        if (isMale) {
            // สูตรสำหรับเพศชาย
            return (1.2 * (weight / (height * height))) + (0.23 * age) - 16.4;
        } else {
            // สูตรสำหรับเพศหญิง
            return (1.2 * (weight / (height * height))) + (0.23 * age) - 5.4;
        }
    }
}
