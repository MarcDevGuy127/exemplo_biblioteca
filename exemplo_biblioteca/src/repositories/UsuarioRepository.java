package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNaoEncontradoException;
import model.Usuario;

public class UsuarioRepository {

    // listando usuarios
    private List<Usuario> usuarios = new ArrayList<>();

    // configurando matriculas unicas
    private Set<String> matriculas = new HashSet<>();

    // acesso rápido por matrícula
    private Map<String, Usuario> mapaUsuarios = new HashMap<>();

    // metodo para adicionar usuario

    public void adicionarUsuario(Usuario usuario)
            throws UsuarioDuplicadoException {

        if (matriculas.contains(usuario.getMatricula())) {

            throw new UsuarioDuplicadoException(
                    "Matrícula já cadastrada.");
        }

        usuarios.add(usuario);
        matriculas.add(usuario.getMatricula());
        mapaUsuarios.put(usuario.getMatricula(), usuario);
    }

    // metodo para consultar usuarios por matricula

    public Usuario buscarPorMatricula(String matricula)
            throws UsuarioNaoEncontradoException {

        Usuario usuario = mapaUsuarios.get(matricula);

        if (usuario == null) {

            throw new UsuarioNaoEncontradoException(
                    "Usuário não encontrado.");
        }

        return usuario;
    }

    // metodo para verificar se matricula existe

    public boolean existeMatricula(String matricula) {

        return matriculas.contains(matricula);
    }

    // metodo para remover usuarios

    public void removerUsuario(String matricula)
            throws UsuarioNaoEncontradoException {

        Iterator<Usuario> iterator =
                usuarios.iterator();

        while (iterator.hasNext()) {

            Usuario usuario = iterator.next();

            if (usuario.getMatricula()
                    .equalsIgnoreCase(matricula)) {

                iterator.remove();

                matriculas.remove(matricula);

                mapaUsuarios.remove(matricula);

                return;
            }
        }

        throw new UsuarioNaoEncontradoException(
                "Usuário não encontrado.");
    }

    // metodo para listar todos os usuarios cadastrados

    public List<Usuario> listarTodos() {

        return new ArrayList<>(usuarios);
    }

    // metodo para listar usuarios por tipo Aluno e Professor

    public List<Usuario> listarPorTipo(String tipo) {

        List<Usuario> resultado =
                new ArrayList<>();

        for (Usuario u : usuarios) {

            if (u.getTipoUsuario()
                    .equalsIgnoreCase(tipo)) {

                resultado.add(u);
            }
        }

        return resultado;
    }

    // metodo para consultar a quantidade de usuarios cadastrados

    public int quantidadeUsuarios() {

        return usuarios.size();
    }
}