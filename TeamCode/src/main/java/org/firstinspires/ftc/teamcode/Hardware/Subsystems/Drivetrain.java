package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Software.Variables;

public class Drivetrain {
    private RobotHardware rob;
    private Variables var;

    public Drivetrain(RobotHardware passedRob, Variables passedVar) {
        this.rob = passedRob;
        this.var = passedVar;
    }

    public void TeleOpDrivetrain(Gamepad gamepad, double motorSpeed) {
        // Negate stick Y because pushing forward gives negative values
        double y  = -gamepad.left_stick_y;  // Drive forward/backward
        double x  =  gamepad.left_stick_x;  // Strafe left/right
        double rx =  gamepad.right_stick_x; // Turn left/right

        // Calculate power for standard Mecanum drive
        double lfPow = y + x + rx;
        double lbPow = y - x + rx;
        double rfPow = y - x - rx;
        double rbPow = y + x - rx;

        // Normalize powers so no motor value exceeds 1.0
        double max = Math.max(Math.abs(lfPow), Math.max(Math.abs(lbPow),
                Math.max(Math.abs(rfPow), Math.abs(rbPow))));
        if (max > 1.0) {
            lfPow /= max;
            lbPow /= max;
            rfPow /= max;
            rbPow /= max;
        }

        // Apply power scaled by speed multiplier
        rob.FrontRight.setPower(lfPow * motorSpeed);
        rob.BackLeft.setPower(lbPow * motorSpeed);
        rob.FrontLeft.setPower(rfPow * motorSpeed);
        rob.BackRight.setPower(rbPow * motorSpeed);
    }
}