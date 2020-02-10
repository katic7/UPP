import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmentaTekstaComponent } from './izmenta-teksta.component';

describe('IzmentaTekstaComponent', () => {
  let component: IzmentaTekstaComponent;
  let fixture: ComponentFixture<IzmentaTekstaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmentaTekstaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmentaTekstaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
