DROP TABLE IF EXISTS SKILL_STACKS;
DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS MESSAGES;
DROP TABLE IF EXISTS PROJECTS;
DROP TABLE IF EXISTS USERS;


create table matchnow.users
(
    user_id       bigint auto_increment
        primary key,
    blog_link     varchar(255)                  null,
    email         varchar(255)                  not null,
    github_link   varchar(255)                  null,
    job           varchar(255)                  null,
    password      varchar(255)                  not null,
    username      varchar(255)                  not null,
    created_date  datetime(6)                   null,
    modified_date datetime(6)                   null,
    role          varchar(255) default 'MEMBER' not null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table matchnow.messages
(
    message_id       bigint auto_increment
        primary key,
    main_text        varchar(255)                        not null,
    send_date        timestamp default CURRENT_TIMESTAMP not null,
    title            varchar(255)                        not null,
    recevier_user_id bigint    default 0                 not null,
    sender_user_id   bigint    default 0                 not null,
    recv_deleted     bit       default b'0'              not null,
    send_deleted     bit       default b'0'              not null,
    constraint FK33dryxa6uu3erf90txk0mjpqn
        foreign key (recevier_user_id) references matchnow.users (user_id),
    constraint FKk4mpqp6gfuaelpcamqv01brkr
        foreign key (sender_user_id) references matchnow.users (user_id)
);

create table matchnow.projects
(
    project_id    bigint  auto_increment primary key,
    input_image   varchar(255)         null,
    main_text     varchar(255)         not null,
    now_cnt       int        default 1 not null,
    state         int        default 0 null,
    title         varchar(255)         not null,
    want_cnt      int                  not null,
    user_id       bigint               not null,
    created_date  datetime(6)          null,
    modified_date datetime(6)          null,
    is_deleted    tinyint(1) default 0 not null,
    category      varchar(255)         not null,
    constraint projects_project_id_uindex
        unique (project_id),
    constraint FKhswfwa3ga88vxv1pmboss6jhm
        foreign key (user_id) references matchnow.users (user_id)
);

create table matchnow.comments
(
    comment_id         bigint auto_increment
        primary key,
    is_deleted         bit default b'0' not null,
    comment_text       varchar(255)     not null,
    project_project_id bigint           not null,
    created_date       datetime(6)      not null,
    modified_date      datetime(6)      null,
    comment_user_id    bigint           not null,
    constraint FKk5uynpnpmfaa4xpv4u4hy5o58
        foreign key (project_project_id) references matchnow.projects (project_id),
    constraint FKp4nvv6gm20q63fcxtevikg1c8
        foreign key (comment_user_id) references matchnow.users (user_id)
);

create table matchnow.skill_stacks
(
    skill_stack_id bigint auto_increment primary key,
    stack_name     varchar(255) not null,
    user_skill_id  bigint       null,
    constraint skill_stacks_skill_stack_id_uindex
        unique (skill_stack_id),
    constraint user_skill_id
        unique (user_skill_id, stack_name),
    constraint FK42mtsl02e8kg2rspyywrq8ejt
        foreign key (user_skill_id) references matchnow.users (user_id)
);
