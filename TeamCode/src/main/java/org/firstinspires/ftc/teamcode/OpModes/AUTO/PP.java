package org.firstinspires.ftc.teamcode.OpModes.AUTO;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "AutoPath", group = "Autonomous")
public class PP extends LinearOpMode {

    private Follower follower;

    private final PoseFactory p = PoseFactory.degrees();

    // Starting pose
    private final Pose start = p.of(56, 9.5, 90);

    // Path 1
    private final Pose path1 = p.of(11.2705, 11.0121, 90);
    private final Pose path1Control1 = p.of(18.014, 27.0765, 0);

    // Path 2
    private final Pose point2 = p.of(61.428, 132.1801, 90);

    private final Pose point2Control1 =
            p.of(41.7214, 31.3189, 0);

    private final Pose point2Control2 =
            p.of(1.2436, 140.1377, 0);

    private final Pose point2Control3 =
            p.of(62.107, 108.3983, 0);

    // Path 3
    private final Pose point3 = p.of(14.5159, 118.7436, 90);

    private final Pose point3Control1 =
            p.of(69.5911, 103.5996, 0);


    // Create path 1 with constant heading
    public Path path1() {
        return Paths.curve(
                start,
                path1Control1,
                path1
        ).constant(start);
    }


    // Create path 2 with constant heading
    public Path path2() {
        return Paths.curve(
                path1,
                point2Control1,
                point2Control2,
                point2Control3,
                point2
        ).constant(path1);
    }


    // Create path 3 with constant heading
    public Path path3() {
        return Paths.curve(
                point2,
                point3Control1,
                point3
        ).constant(point2);
    }


    // Run all paths in sequence
    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3())
        );
    }


    @Override
    public void runOpMode() {

        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        telemetry.addLine("AutoPath Ready");
        telemetry.addLine("Place robot at the programmed start pose.");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        schedule(autoRoutine());

        while (opModeIsActive()) {

            follower.update();
            Scheduler.execute();

            Pose current = follower.pose();

            telemetry.addData("X", "%.2f", current.x());
            telemetry.addData("Y", "%.2f", current.y());

            // Display heading in degrees
            telemetry.addData(
                    "Heading",
                    "%.1f",
                    Math.toDegrees(current.heading())
            );

            telemetry.addData("Follower Mode", follower.mode());

            if (follower.currentPath() != null) {
                telemetry.addData(
                        "Distance Remaining",
                        "%.2f",
                        follower.distanceToEndpoint()
                );

                telemetry.addData(
                        "Path Index",
                        follower.pathIndex()
                );
            } else {
                telemetry.addLine("No active path");
            }

            telemetry.update();
        }
    }
}
