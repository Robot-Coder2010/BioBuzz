package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Software.Variables;

@TeleOp
public class TeleOp_Test extends OpMode {
    public static int WTH = 32;
    Variables var;
    RobotHardware rob;
    Drivetrain drivetrain;
    LLResult llInfo;

    public float Speed = 0.5f; // Default starting speed

    // State trackers for edge detection (prevents multi-firing when holding buttons)
    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;

    @Override
    public void init() {
        var = new Variables();
        rob = new RobotHardware(hardwareMap);
        drivetrain = new Drivetrain(rob, var);
    }

    @Override
    public void loop() {
        // --- 1. D-Pad Speed Increments (0.05 steps) ---
        boolean currentDpadUp = gamepad1.dpad_up;
        boolean currentDpadDown = gamepad1.dpad_down;

        // If Dpad Up was just pressed this frame, increase speed by 0.05 (capped at 1.0)
        if (currentDpadUp && !lastDpadUp) {
            Speed = Math.min(1.0f, Speed + 0.05f);
        }

        // If Dpad Down was just pressed this frame, decrease speed by 0.05 (floored at 0.0)
        if (currentDpadDown && !lastDpadDown) {
            Speed = Math.max(0.0f, Speed - 0.05f);
        }

        // Save current states for the next loop iteration
        lastDpadUp = currentDpadUp;
        lastDpadDown = currentDpadDown;

        // --- 2. Motor Controls ---
        if (gamepad1.b) {
            // Button B runs the motor at your dynamically adjusted Speed
            rob.LaunchMotor.setPower(Speed);
        } else if (gamepad1.right_trigger > 0.1) {
            // Right trigger can still control it directly if desired
            rob.LaunchMotor.setPower(gamepad1.right_trigger);
        } else {
            rob.LaunchMotor.setPower(0);
        }

        // --- 3. Telemetry Feedback ---
        telemetry.addData("Current Speed Setting", String.format("%.2f", Speed));
        telemetry.addData("Launch Motor Power", rob.LaunchMotor.getPower());
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
        telemetry.update();
    }
}