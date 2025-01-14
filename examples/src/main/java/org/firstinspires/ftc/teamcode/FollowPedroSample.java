package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.Path;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathChainCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

@Autonomous
public class FollowPedroSample extends CommandOpMode {
    Follower follower = new Follower(hardwareMap);
    private final ArrayList<PathChain> paths = new ArrayList<>();
    PathChain pathChain = new PathChain();
    Path path = new Path(
            new BezierCurve(
                    new Point(20, 30, Point.CARTESIAN),
                    new Point(50, 80, Point.CARTESIAN)
            )
    );

    public void generatePath() {
        paths.add(
                // An example path
                follower.pathBuilder()
                        .addPath(
                                path
                        )
               .build()
        );
    }

    @Override
    public void initialize() {
        super.reset();

        generatePath();

        schedule(
                // Updates follower to follow path
                new RunCommand(() -> follower.update()),

                new FollowPathChainCommand(follower, paths.get(0)),
                new FollowPathChainCommand(follower, pathChain),
                new FollowPathCommand(follower, path)
        );
    }

    @Override
    public void run() {
        super.run();

    }
}