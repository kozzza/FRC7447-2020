/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


/**
 * Add your docs here.
 */
public class AutonWheelSpinner extends Subsystem {

  public WPI_VictorSPX colorSpinner = new WPI_VictorSPX(7);
  
  

    
  

  public void scrollMovement(double percentVoltage) {
    colorSpinner.set(ControlMode.PercentOutput, percentVoltage);
  }

  

  

  
  

  // public void intakeSpin(double percentVoltage) {
  //   intakeMaster.set(ControlMode.PercentOutput, percentVoltage);
  //   intakeSlave.set(ControlMode.PercentOutput, percentVoltage);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
