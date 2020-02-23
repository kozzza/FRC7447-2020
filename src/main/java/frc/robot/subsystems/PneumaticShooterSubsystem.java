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

  private final DoubleSolenoid doubleSol = new DoubleSolenoid(0,1);


  public void ShooterToggle(int solenoidToggle) {
    System.out.println("Toggling:");
    if (solenoidToggle == 1) {
      doubleSol.set(DoubleSolenoid.Value.kForward);
      System.out.println("Solenoid on");
    } 
    else if (solenoidToggle == -1) {
      doubleSol.set(DoubleSolenoid.Value.kReverse);
      System.out.println("Solenoid off");
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
