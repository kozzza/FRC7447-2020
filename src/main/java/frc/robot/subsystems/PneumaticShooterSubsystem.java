/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PneumaticShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(0,1);
  private final DoubleSolenoid doubleSolenoid2 = new DoubleSolenoid(2,3);

  public void ShooterToggle(final int solenoidToggle) {
    System.out.println("Toggling:");
    if (solenoidToggle == -1) {
      doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      System.out.println("Solenoid on");
    } 
    else if (solenoidToggle == 1) {
      doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
      System.out.println("Solenoid off");
    }
  }
  public void Solenoidclimbonoff(final boolean solenoidToggle) {
    System.out.println("Climb Toggling");
    if (solenoidToggle) {
      doubleSolenoid2.set(DoubleSolenoid.Value.kForward);
      System.out.println("~Climb Initiated~");
    }
    else {
      doubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
      System.out.println("~Climb Deactivated~");
    }

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
