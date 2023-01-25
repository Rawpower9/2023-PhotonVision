package frc.robot.commands;

import java.util.Set;

import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.PhotonVision;

public class DistanceFromTag extends CommandBase{
    PhotonVision pv;
    public DistanceFromTag(){
        pv = new PhotonVision();
    }
    

    public double dist(){
        PhotonPipelineResult result = pv.getLatestPipeline();
        double dist = 0.0;
        if(result.hasTargets()){
            dist = PhotonUtils.calculateDistanceToTargetMeters(1, 1, 0.349066, 0);
            
        }
        else{
            dist = 0.69;
        }
        return dist;

    }

    
    
    // @Override
    // public Set<Subsystem> getRequirements(){
    //     return Set.of(pv);
    // }
}