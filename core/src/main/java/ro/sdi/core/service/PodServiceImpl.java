package ro.sdi.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ro.sdi.core.exceptions.ElementNotFoundException;
import ro.sdi.core.model.Pod;
import ro.sdi.core.repository.PodRepository;

@Slf4j
@Service
public class PodServiceImpl implements PodService
{

    @Autowired
    private PodRepository podRepository;

    @Override
    public Pod add(String name, Integer cost)
    {
        Pod pod = new Pod(name, cost);
        log.trace("Adding pod {}", pod);

        return podRepository.save(pod);
    }

    @Override
    public List<Pod> getPods()
    {
        return podRepository.findAll();
    }

    @Override
    public List<Pod> getAvailablePods()
    {
        return podRepository.findAll()
                            .stream()
                            .filter(pod -> pod.getNode() == null)
                            .collect(Collectors.toList());
    }

    @Override
    public Pod delete(Long podId)
    {
        Pod pod = podRepository.findById(podId)
                               .orElseThrow(() -> new ElementNotFoundException("Pod"));
        podRepository.deleteById(podId);
        return pod;
    }
}
