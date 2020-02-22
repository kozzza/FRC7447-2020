/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

/**
 * Add your docs h
 * //eyyeyeyeere.
 */
public class PneumaticControl extends Subsystem {
Compressor c = new Compressor(6);



boolean pressureSwitch = c.getPressureSwitchValue();
double current = c.getCompressorCurrent();




  DoubleSolenoid firstDoubleSolenoid = new DoubleSolenoid(RobotMap.solValve1, RobotMap.solValve2);

  DoubleSolenoid secondDoubleSolenoid = new DoubleSolenoid(RobotMap.solValve3, RobotMap.solValve4);

  public int direction = -1;
  public int direction1 = -1;
 
  public void pneumaticControlLauncher() {
    boolean enabled = c.enabled();
    System.out.println(enabled);
    secondDoubleSolenoid.set(Value.kOff);
    
    direction *= -1;

    if (direction == -1) {
      firstDoubleSolenoid.set(Value.kForward);

    }
    else if (direction == 1) {
      firstDoubleSolenoid.set(Value.kReverse);
    }
  }
   public void pneumaticControlClimb() {

     firstDoubleSolenoid.set(Value.kOff);
    direction1 *= -1;

    if (direction1 == -1) {
      secondDoubleSolenoid.set(Value.kForward);
      

    }
    else if (direction1 == 1) {
      secondDoubleSolenoid.set(Value.kReverse);
      
    }

  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new PnuControlCommand());
  }
}
