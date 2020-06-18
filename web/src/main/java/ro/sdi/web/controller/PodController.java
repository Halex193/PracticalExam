package ro.sdi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ro.sdi.core.exceptions.AlreadyExistingElementException;
import ro.sdi.core.exceptions.ElementNotFoundException;
import ro.sdi.core.model.Pod;
import ro.sdi.core.service.PodService;
import ro.sdi.web.converter.PodConverter;
import ro.sdi.web.dto.PodDto;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Slf4j
@RestController
public class PodController
{
    private final PodService podService;

    private final PodConverter podConverter;

    public PodController(
            PodService podService,
            PodConverter podConverter
    )
    {
        this.podService = podService;
        this.podConverter = podConverter;
    }


    @RequestMapping(value = "/pods", method = GET)
    public List<PodDto> getPods()
    {
        List<Pod> pods = podService.getPods();
        log.trace("Get pods: {}", pods);
        return podConverter.toDtos(pods);
    }

    @RequestMapping(value = "/pods", method = POST)
    public ResponseEntity<?> addPod(@RequestBody PodDto podDto)
    {
        Pod pod = podConverter.toModel(podDto);
        try
        {
            podService.add(pod.getName(), pod.getCost());
        }
        catch (AlreadyExistingElementException e)
        {
            log.trace("Pod {} already exists", pod);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        log.trace("Pod {} added", pod);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pods/{id}", method = DELETE)
    public ResponseEntity<?> deletePod(@PathVariable long id)
    {
        try
        {
            podService.delete(id);
        }
        catch (ElementNotFoundException e)
        {
            log.trace("Pod with id {} could not be deleted", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.trace("Pod with id {} was deleted", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@RequestMapping(value = "/pods/filter/{name}", method = GET)
    public List<PodDto> filterPodsByName(@PathVariable String name)
    {
        return podConverter.toDtos(podService.filterPodsByName(name));
    }*/
}
