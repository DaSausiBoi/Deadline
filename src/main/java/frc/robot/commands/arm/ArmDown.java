package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Arm;

public class ArmDown extends Command {
    Arm arm;

    public ArmDown(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.setArmVelo(0.7); // -0.4 with neo 550
    }

    @Override
    public void end(boolean interrupted) {
        arm.setArmVelo(0);
    }
}