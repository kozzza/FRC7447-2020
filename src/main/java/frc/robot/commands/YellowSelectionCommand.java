/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class YellowSelectionCommand extends Command {
  final Color kBlueTarget = ColorMatch.makeColor(0, 255, 255);
  final Color kGreenTarget = ColorMatch.makeColor(0, 255, 0);
  final Color kRedTarget = ColorMatch.makeColor(255, 0, 0);
  final Color kYellowTarget = ColorMatch.makeColor(255, 255, 0);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  String colorString;
  double counter = 0;
  Boolean isComplete;

  final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
     final ColorMatch m_colorMatcher = new ColorMatch();

   
  public YellowSelectionCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.wheelSpinner);
  }



  @Override
  protected void initialize() {
    
  
   
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Color detectedColor = m_colorSensor.getColor();
    // getting color value of sensor
    
    // Robot.wheelSpinner.colorchange(0);
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    while (match.color != kYellowTarget && match.confidence < .5 && counter != 2){
      if (match.color == kYellowTarget && counter != 2) {
        counter += 1;
        Robot.wheelSpinner.scrollMovement(.024);
      }
      else if (match.color == kYellowTarget && match.confidence > .5 && counter == 1) {
          counter +=1;
          Robot.wheelSpinner.scrollMovement(-.02);
          isComplete = true;
      }
      else {
        Robot.wheelSpinner.scrollMovement(.03);
      }
      }

    }
  
  @Override
  protected boolean isFinished() {
    return isCompleted();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    counter = 0;
    
    
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

