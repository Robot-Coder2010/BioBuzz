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
        drivetrain.TeleOpDrivetrain(gamepad1, 0.5);

        // Override controls if B is pressed
        if (gamepad1.b) {
            rob.BackLeft.setPower(1.0);
            rob.FrontRight.setPower(1.0);
            rob.BackRight.setPower(1.0);
            rob.FrontLeft.setPower(1.0);
        }else{
            rob.BackLeft.setPower(0.0);
            rob.FrontRight.setPower(0.0);
            rob.BackRight.setPower(0.0);
            rob.FrontLeft.setPower(0.0);
        }

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}