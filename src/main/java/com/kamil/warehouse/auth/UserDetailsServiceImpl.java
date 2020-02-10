package com.kamil.warehouse.auth;

import com.kamil.warehouse.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service // dziedziczy po component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public UserDetails loadUserByUsername(String ident) throws UsernameNotFoundException {
        return operatorRepository.findByIdent(ident) // pobieram ident operatora, metoda findByIdent zwraca Optional<Operator>
                .map(operator -> new User(operator.getIdent(), operator.getPassword(), operator.getRoles() //  mapuję Operatora na usera z Security
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())) // z roli dla operatora tworzę role z security
                        .collect(Collectors.toSet()))) // tworzy Set
                .orElseThrow(() -> new UsernameNotFoundException(ident));
    }
}
