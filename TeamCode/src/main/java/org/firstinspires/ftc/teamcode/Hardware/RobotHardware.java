package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    public DcMotor FrontRight, FrontLeft, BackLeft, BackRight;
    //public Servo servo;

    public RobotHardware(HardwareMap hwMap) {

        //Motors
        FrontRight = hwMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hwMap.get(DcMotor.class, "FrontLeft");
        BackLeft = hwMap.get(DcMotor.class, "BackLeft");
        BackRight = hwMap.get(DcMotor.class, "BackRight");

        // Standard setup: Reverse left side, Keep right side forward
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.FORWARD);

        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Servos
        //servo = hwMap.get(Servo.class, "servo");
        //servo.setDirection(Servo.Direction.FORWARD);
    }
}