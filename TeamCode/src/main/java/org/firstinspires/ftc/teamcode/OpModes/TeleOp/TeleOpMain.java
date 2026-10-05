package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Variables;

@TeleOp
public class TeleOpMain extends OpMode {
    public static int WTH = 32;
    Variables var;
    RobotHardware rob;
    Drivetrain drivetrain;
    LLResult llInfo;

    @Override
    public void init() {
        var = new Variables();
        rob = new RobotHardware(hardwareMap);
        drivetrain = new Drivetrain(rob, var);
    }

    @Override
    public void loop() {
        // Driver controls standard movement
        drivetrain.TeleOpDrivetrain(gamepad1, 0.95);
        //rob.servo.setPosition(gamepad1.right_trigger);

        telemetry.addData("Gamepad 1 - Right Trigger Value:", gamepad1.right_trigger);
        telemetry.update();
    }
}