package br.com.fiap.bo;

import br.com.fiap.dao.*;
import br.com.fiap.to.*;

import java.util.ArrayList;

public class AgendaBO {
    private AgendaDAO agendaDAO;
    private ServicoAgendadoDAO servicoAgendadoDAO;
    private ServicoProvisionadoDAO servicoProvisionadoDAO;
    private DiagnosticoDAO diagnosticoDAO;
    private ServicoDAO servicoDAO;
    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;
    private OficinaDAO oficinaDAO;
    private UsuarioDAO usuarioDAO;

    public ArrayList<AgendaTO> findAll() {
        AgendaDAO agendaDAO = new AgendaDAO();
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        OficinaDAO oficinaDAO = new OficinaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        ArrayList<AgendaTO> agendas = agendaDAO.findAll();

        for (AgendaTO agenda : agendas) {
            ArrayList<ServicoAgendadoTO> servicosAgendados = servicoAgendadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoAgendadoTO servicoAgendado : servicosAgendados) {
                ServicoTO servico = servicoDAO.findById(servicoAgendado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoAgendado.setServico(servico);
            }
            agenda.setServicosAgendados(servicosAgendados);

            ArrayList<ServicoProvisionadoTO> servicosProvisionados = servicoProvisionadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoProvisionadoTO servicoProvisionado : servicosProvisionados) {
                ServicoTO servico = servicoDAO.findById(servicoProvisionado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoProvisionado.setServico(servico);
            }
            agenda.setServicosProvisionados(servicosProvisionados);

            ArrayList<DiagnosticoTO> diagnosticos = diagnosticoDAO.findByAgendamentoId(agenda.getId());
            for (DiagnosticoTO diagnostico : diagnosticos) {
                VeiculoTO veiculo = veiculoDAO.findById(diagnostico.getVeiculo().getId());
                if (veiculo != null) {
                    ClienteTO cliente = clienteDAO.findById(veiculo.getCliente().getId());
                    if (cliente != null) {
                        UsuarioTO usuarioCliente = usuarioDAO.findById(cliente.getUsuario().getId());
                        cliente.setUsuario(usuarioCliente);
                    }
                    veiculo.setCliente(cliente);
                }
                diagnostico.setVeiculo(veiculo);
            }
            agenda.setDiagnosticos(diagnosticos);
        }

        return agendas;
    }

    public ArrayList<AgendaTO> findByOficinaID(Long oficinaId) {
        AgendaDAO agendaDAO = new AgendaDAO();
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        OficinaDAO oficinaDAO = new OficinaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        ArrayList<AgendaTO> agendas = agendaDAO.findByOficinaId(oficinaId);

        for (AgendaTO agenda : agendas) {
            // Process servicosAgendados
            ArrayList<ServicoAgendadoTO> servicosAgendados = servicoAgendadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoAgendadoTO servicoAgendado : servicosAgendados) {
                ServicoTO servico = servicoDAO.findById(servicoAgendado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoAgendado.setServico(servico);
            }
            agenda.setServicosAgendados(servicosAgendados);

            // Process servicosProvisionados
            ArrayList<ServicoProvisionadoTO> servicosProvisionados = servicoProvisionadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoProvisionadoTO servicoProvisionado : servicosProvisionados) {
                ServicoTO servico = servicoDAO.findById(servicoProvisionado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoProvisionado.setServico(servico);
            }
            agenda.setServicosProvisionados(servicosProvisionados);

            // Process diagnosticos
            ArrayList<DiagnosticoTO> diagnosticos = diagnosticoDAO.findByAgendamentoId(agenda.getId());
            for (DiagnosticoTO diagnostico : diagnosticos) {
                VeiculoTO veiculo = veiculoDAO.findById(diagnostico.getVeiculo().getId());
                if (veiculo != null) {
                    ClienteTO cliente = clienteDAO.findById(veiculo.getCliente().getId());
                    if (cliente != null) {
                        UsuarioTO usuarioCliente = usuarioDAO.findById(cliente.getUsuario().getId());
                        cliente.setUsuario(usuarioCliente);
                    }
                    veiculo.setCliente(cliente);
                }
                diagnostico.setVeiculo(veiculo);
            }
            agenda.setDiagnosticos(diagnosticos);
        }

        return agendas;
    }

    public ArrayList<AgendaTO> findByClienteID(Long clienteId) {
        AgendaDAO agendaDAO = new AgendaDAO();
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        OficinaDAO oficinaDAO = new OficinaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        ArrayList<AgendaTO> agendas = agendaDAO.findByClienteId(clienteId);

        for (AgendaTO agenda : agendas) {
            // Process servicosAgendados
            ArrayList<ServicoAgendadoTO> servicosAgendados = servicoAgendadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoAgendadoTO servicoAgendado : servicosAgendados) {
                ServicoTO servico = servicoDAO.findById(servicoAgendado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoAgendado.setServico(servico);
            }
            agenda.setServicosAgendados(servicosAgendados);

            // Process servicosProvisionados
            ArrayList<ServicoProvisionadoTO> servicosProvisionados = servicoProvisionadoDAO.findByAgendamentoId(agenda.getId());
            for (ServicoProvisionadoTO servicoProvisionado : servicosProvisionados) {
                ServicoTO servico = servicoDAO.findById(servicoProvisionado.getServico().getId());
                if (servico != null) {
                    OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());
                    if (oficina != null) {
                        UsuarioTO usuarioOficina = usuarioDAO.findById(oficina.getUsuario().getId());
                        oficina.setUsuario(usuarioOficina);
                    }
                    servico.setOficina(oficina);
                }
                servicoProvisionado.setServico(servico);
            }
            agenda.setServicosProvisionados(servicosProvisionados);

            // Process diagnosticos
            ArrayList<DiagnosticoTO> diagnosticos = diagnosticoDAO.findByAgendamentoId(agenda.getId());
            for (DiagnosticoTO diagnostico : diagnosticos) {
                VeiculoTO veiculo = veiculoDAO.findById(diagnostico.getVeiculo().getId());
                if (veiculo != null) {
                    ClienteTO cliente = clienteDAO.findById(veiculo.getCliente().getId());
                    if (cliente != null) {
                        UsuarioTO usuarioCliente = usuarioDAO.findById(cliente.getUsuario().getId());
                        cliente.setUsuario(usuarioCliente);
                    }
                    veiculo.setCliente(cliente);
                }
                diagnostico.setVeiculo(veiculo);
            }
            agenda.setDiagnosticos(diagnosticos);
        }

        return agendas;
    }

    public AgendaTO save(AgendaTO agenda) {
        AgendaDAO agendaDAO = new AgendaDAO();
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        ServicoDAO servicoDAO = new ServicoDAO();
        OficinaDAO oficinaDAO = new OficinaDAO();

        for (ServicoAgendadoTO servicoAgendado : agenda.getServicosAgendados()) {
            ServicoTO servico = servicoDAO.findById(servicoAgendado.getServico().getId());
            OficinaTO oficina = oficinaDAO.findById(servico.getOficina().getId());

            if (!agenda.matchesHorarioDisponivel(oficina.getHorariosDisponiveis())) {
                return null;
            }
        }

        AgendaTO savedAgenda = agendaDAO.save(agenda);

        for (ServicoAgendadoTO servicoAgendado : agenda.getServicosAgendados()) {
            servicoAgendado.setAgendamento(savedAgenda);
            servicoAgendadoDAO.save(servicoAgendado);
        }

        for (ServicoProvisionadoTO servicoProvisionado : agenda.getServicosProvisionados()) {
            servicoProvisionado.setAgendamento(savedAgenda);
            servicoProvisionadoDAO.save(servicoProvisionado);
        }

        for (DiagnosticoTO diagnostico : agenda.getDiagnosticos()) {
            diagnostico.setAgenda(savedAgenda);
            diagnosticoDAO.save(diagnostico);
        }

        return savedAgenda;
    }

    public AgendaTO update(AgendaTO agenda) {
        AgendaDAO agendaDAO = new AgendaDAO();
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();

        agendaDAO.update(agenda);

        for (ServicoAgendadoTO servicoAgendado : agenda.getServicosAgendados()) {
            servicoAgendado.setAgendamento(agenda);
            servicoAgendadoDAO.update(servicoAgendado);
        }

        for (ServicoProvisionadoTO servicoProvisionado : agenda.getServicosProvisionados()) {
            servicoProvisionado.setAgendamento(agenda);
            servicoProvisionadoDAO.update(servicoProvisionado);
        }

        for (DiagnosticoTO diagnostico : agenda.getDiagnosticos()) {
            diagnostico.setAgenda(agenda);
            diagnosticoDAO.update(diagnostico);
        }

        return agenda;
    }

    public boolean delete(long id) {
        ServicoAgendadoDAO servicoAgendadoDAO = new ServicoAgendadoDAO();
        ServicoProvisionadoDAO servicoProvisionadoDAO = new ServicoProvisionadoDAO();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
        AgendaDAO agendaDAO = new AgendaDAO();

        ArrayList<ServicoAgendadoTO> servicosAgendados = servicoAgendadoDAO.findByAgendamentoId(id);
        for (ServicoAgendadoTO servicoAgendado : servicosAgendados) {
            servicoAgendadoDAO.delete(servicoAgendado.getId());
        }

        ArrayList<ServicoProvisionadoTO> servicosProvisionados = servicoProvisionadoDAO.findByAgendamentoId(id);
        for (ServicoProvisionadoTO servicoProvisionado : servicosProvisionados) {
            servicoProvisionadoDAO.delete(servicoProvisionado.getId());
        }

        ArrayList<DiagnosticoTO> diagnosticos = diagnosticoDAO.findByAgendamentoId(id);
        for (DiagnosticoTO diagnostico : diagnosticos) {
            diagnosticoDAO.delete(diagnostico.getId());
        }

        return agendaDAO.delete(id);
    }
}
