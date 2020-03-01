/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;


/**
 * Add your docs here.
 */
public class WheelSpinner extends Subsystem {

  public WPI_VictorSPX colorSpinner = new WPI_VictorSPX(8);
  final Color kBlueTarget = ColorMatch.makeColor(0, 255, 255);
  final Color kGreenTarget = ColorMatch.makeColor(0, 255, 0);
  final Color kRedTarget = ColorMatch.makeColor(255, 0, 0);
  final Color kYellowTarget = ColorMatch.makeColor(255, 255, 0);
  
  public void scrollMovement(final double percentVoltage) {
    colorSpinner.set(ControlMode.PercentOutput, percentVoltage);
  }

  public void colorChange(final int cycle) {
    
  // final Color Target = ColorMatch.makeColor(0, 255, 255);
  // final Color kGreenTarget = ColorMatch.makeColor(0, 255, 0);
  // final Color kRedTarget = ColorMatch.makeColor(255, 0, 0);
  // final Color kYellowTarget = ColorMatch.makeColor(255, 255, 0);

  final I2C.Port i2cPort = I2C.Port.kOnboard;
  String colorString;
  double counter = 0;
  Boolean isComplete;
  

  final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
     final ColorMatch m_colorMatcher = new ColorMatch();
     Color detectedColor = m_colorSensor.getColor();
     
     


  if (cycle == 1) {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    final Color Target = ColorMatch.makeColor(0, 255, 255);
    System.out.println("######### B L U E ######## ");
    
    
    // while (match.color != Target && match.confidence < .5 && counter != 2){
      if (match.color == Target && counter != 2 ) {
        counter += 1;
        colorSpinner.set(ControlMode.PercentOutput, .024);
      }
      else if (match.color == Target && match.confidence > .5 && counter == 1) {
          counter +=1;
          colorSpinner.set(ControlMode.PercentOutput, -.024);
          isComplete = true;
      }
      else {
        colorSpinner.set(ControlMode.PercentOutput, .03);
      }
  }
  else if (cycle == 2) {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    final Color Target = ColorMatch.makeColor(0, 255, 0);
    System.out.println("####### G R E E N #########");
    // while (match.color != Target && match.confidence < .5 && counter != 2){
      if (match.color == Target && counter != 2 ) {
        counter += 1;
        colorSpinner.set(ControlMode.PercentOutput, .024);
      }
      else if (match.color == Target && match.confidence > .5 && counter == 1) {
          counter +=1;
          colorSpinner.set(ControlMode.PercentOutput, -.024);
          isComplete = true;
      }
      else {
        colorSpinner.set(ControlMode.PercentOutput, .03);
      }
  }
  else if (cycle == 3) {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    final Color Target = ColorMatch.makeColor(0, 255, 0);
    System.out.println("######### R E D #########");
    // while (match.color != Target && match.confidence < .5 && counter != 2){
      if (match.color == Target && counter != 2 ) {
        counter += 1;
        colorSpinner.set(ControlMode.PercentOutput, .024);
      }
      else if (match.color == Target && match.confidence > .5 && counter == 1) {
          counter +=1;
          colorSpinner.set(ControlMode.PercentOutput, -.024);
          isComplete = true;
      }
      else {
        colorSpinner.set(ControlMode.PercentOutput, .03);
      }
  }
  else if (cycle == 4) {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    System.out.println("########## Y E L L O W ############ ");
    final Color Target = ColorMatch.makeColor(255, 255, 0);
    // while (match.color != Target && match.confidence < .5 && counter != 2){
      if (match.color == Target && counter != 2 ) {
        counter += 1;
        colorSpinner.set(ControlMode.PercentOutput, .024);
      }
      else if (match.color == Target && match.confidence > .5 && counter == 1) {
          counter +=1;
          colorSpinner.set(ControlMode.PercentOutput, -.024);
          isComplete = true;
      }
      else {
        colorSpinner.set(ControlMode.PercentOutput, .03);
      }
  }
}

  


  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
