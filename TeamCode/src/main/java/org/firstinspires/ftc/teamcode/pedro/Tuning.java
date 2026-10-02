package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.pedro.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.PinpointTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.Tests;

public class Tuning {
    // Tuners go here
    @Tuner
    public static Procedure tests() {
        return new Tests(hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig), (hardwareMap -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig)), null);
    }
    @Tuner
    public static Procedure foresightTuner() {
        return new ForesightTuner((hardwareMap) -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig), (hardwareMap) -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
    }
}


//This is what came out of the tuning process I just put it down here so you could put it in the correct spot BTW I also put the new giant code on PedroPathingCode.txt


public static ForesightConfig foresightConfig = new ForesightConfig(
        c -> {
            Controller primaryTranslationalForward = Controller.proportional(0.21059195735546496);
            Controller secondaryTranslationalForward = Controller.proportional(0.0778081011221076);
            Controller primaryTranslationalLateral = Controller.proportional(3.4662089103689655);
            Controller secondaryTranslationalLateral = Controller.proportional(1.2806715735734626);

            c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
            c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

            c.coast.set(Controller.proportionalFeedforward(0.015920186327228462));
            c.brake.set(Controller.proportionalFeedforward(0.013532158378144192));

            c.headingFeedback.set(Controller.proportional(2.047429012911527));
            c.headingBrakeCoefficients.set(Vector2D.cartesian(0.036404442342783394, 0.00666736828771071));

            c.linearBrakeCoefficients.set(Matrix.diag(0.052148048412713664, 0.04562143035702261));
            c.quadraticBrakeCoefficients.set(Matrix.diag(0.0014992129564496532, 0.0016488335956857478));

            c.maxAchievableForwardVelocity.set(63.912349409160925);
            c.maxAchievableStrafeVelocity.set(52.52340443909354);
            c.naturalForwardDeceleration.set(51.80177603612134);
            c.naturalStrafeDeceleration.set(60.22020177932905);
        }
);