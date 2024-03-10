package com.pat.keycloak.resource.admin.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.services.resources.admin.permissions.AdminPermissionEvaluator;
import org.keycloak.services.resources.admin.permissions.RealmPermissionEvaluator;
import org.keycloak.services.resources.admin.permissions.RolePermissionEvaluator;
import org.keycloak.services.resources.admin.permissions.UserPermissionEvaluator;

import com.pat.keycloak.resource.admin.mappers.RoleMapper;
import com.pat.keycloak.resource.admin.models.RoleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
// @Path("/admin/realms/{realm}/" + MyAdminRealmResourceProvider.PROVIDER_ID)
public class MyAdminRealmResource {

  private final KeycloakSession session;
  private final RealmModel realm;
  private final AdminPermissionEvaluator auth;

  // private static final Logger log =
  // Logger.getLogger(MyAdminRealmResource.class);

  @GET
  @Path("getRolesByArrtibute")
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getRolesByArrtibute(
      @QueryParam("attr") String attr) {
    final RoleMapper roleMapper = new RoleMapper();

    final RealmPermissionEvaluator realmPermissionEvaluator = auth.realm();
    realmPermissionEvaluator.requireViewRealm();

    List<RoleModel> myRoles = session.roles().getRealmRolesStream(realm)
        .collect(Collectors.toList());
    List<RoleDto> myRoleDtos = new ArrayList<RoleDto>();

    for (RoleModel role : myRoles) {
      final List<String> restartAttribute = role.getAttributes().get(attr);
      if (restartAttribute != null && restartAttribute.size() > 0) {
        myRoleDtos.add(roleMapper.mapToRoleDto(role));
      }
    }

    return Response.status(Response.Status.OK).entity(myRoleDtos).build();
  }

}