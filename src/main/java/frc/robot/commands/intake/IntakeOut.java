package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Intake;

public class IntakeOut extends Command {
    Intake intake;

    public IntakeOut(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntakeVelo(0.7); // -0.4 with neo 550
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakeVelo(0);
    }
}