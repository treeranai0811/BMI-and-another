/**
 * 
 * @author กลุ่มหิว
 */
//                คลาสแม่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่่
package com.example.demo;

public abstract class Body {
    protected double weight;
    protected double height;

    /**
     * 
     * @param weight ค่าน้ำหนักของผู้ใช้ที่กรอกมา (กิโลกรัม)
     * @param height ค่าส่วนสูงของผู้ใช้ที่กรอกมา (เช็นติเมตร)
     */
    public Body(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    /**
     * 
     * @return ค่าผลลัพธ์ที่ได้จากการคำนวณของแต่ละคลาสที่เรียกใช้
     */
    public abstract double calculate();
}