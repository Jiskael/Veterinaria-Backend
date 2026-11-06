package service;

import model.Duenio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DuenioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DuenioService {

    @Autowired
    private DuenioRepository duenioRepository;

    public List<Duenio> getDuenios() {
        return duenioRepository.findAll();
    }

    public Optional<Duenio> getDuenio(Integer id) {
        return duenioRepository.findById(id);
    }

    public Duenio saveDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public Duenio updateDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public void deleteDuenio(Integer id) {
        duenioRepository.deleteById(id);
    }
}
