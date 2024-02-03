package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.AutoDriveBackCommand;

import frc.robot.Constants.UsbConstants;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Arm;

import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.intake.IntakeOut;
import frc.robot.commands.arm.ArmUp;
import frc.robot.commands.arm.ArmDown;

public class RobotContainer {
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake m_intake = new Intake();
    private final Arm m_arm = new Arm();

    private final IntakeIn m_intakeIn = new IntakeIn(m_intake);
    private final IntakeOut m_intakeOut = new IntakeOut(m_intake);
    private final ArmUp m_armUp = new ArmUp(m_arm);
    private final ArmDown m_armDown = new ArmDown(m_arm);

    private final CommandXboxController driverController = new CommandXboxController(UsbConstants.DRIVER_CONTROLLER_PORT);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

    public RobotContainer() {
        // add negative (-) to getLeftY to invert drive (shooter will be the back, intake will be the front)
        configureButtonBindings();

        initializeAutoChooser();
    
        drivetrain.setDefaultCommand(new RunCommand(() -> drivetrain.arcadeDrive(
          driverController.getRightX(),
          driverController.getLeftY()),
          drivetrain));
      }

      private void configureButtonBindings() {
        driverController.leftBumper().whileTrue(m_intakeIn);
        driverController.x().whileTrue(m_intakeOut);
        driverController.rightTrigger().whileTrue(m_armDown);
        driverController.leftTrigger().whileTrue(m_armUp);

      }

      public void initializeAutoChooser(){
        m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0));
        m_autoChooser.addOption("Drive Back", new WaitCommand(0.1)
          .andThen(new AutoDriveBackCommand(drivetrain).withTimeout(3.8)));
      
       SmartDashboard.putData("Auto Selector", m_autoChooser);
      }
      
      public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return m_autoChooser.getSelected(); 
      }
    
}
