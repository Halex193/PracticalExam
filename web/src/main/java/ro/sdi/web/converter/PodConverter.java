package ro.sdi.web.converter;

import org.springframework.stereotype.Component;

import ro.sdi.core.model.Pod;
import ro.sdi.web.dto.PodDto;

@Component
public class PodConverter implements Converter<Pod, PodDto>
{

    @Override
    public Pod toModel(PodDto pod)
    {
        return new Pod(pod.getName(), pod.getCost());
    }

    @Override
    public PodDto toDto(Pod pod)
    {
        return new PodDto(pod.getName() ,pod.getCost());
    }
}
